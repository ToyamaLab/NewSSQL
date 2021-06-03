#/bin/sh

for OPT in "$@"
do
	case "$OPT" in
		'--installdir' )
			FLAG_installdir=1
			INSTALL_NAME=$2
			;;
	esac
	shift
done

INSTALLDIR=$HOME
if [ "$FLAG_installdir" ]; then
	INSTALLDIR=$INSTALL_NAME
fi


SCRIPT_DIR=$(cd $(dirname $0); pwd)

#SuperSQL用のディレクトリ作成
mkdir -p $INSTALLDIR/SuperSQL

#SuperSQL出力用のディレクトリ作成
mkdir -p $INSTALLDIR/SuperSQL/ssql_result

#SuperSQLクエリ置き場の作成
mkdir -p $INSTALLDIR/SuperSQL/ssql_query

#テストクエリの作成
cat $SCRIPT_DIR/test_queries/DM_All_queries/Q1.ssql > $INSTALLDIR/SuperSQL/ssql_query/test.ssql

#SuperSQLのコンフィグファイルの作成
cat << EOS > $HOME/config.ssql
driver=
db=
host=
user=
outdir=$INSTALLDIR/SuperSQL/ssql_result
port=
EOS

#jarの作成
mvn package
cp $SCRIPT_DIR/target/newssql-1.0-jar-with-dependencies.jar $INSTALLDIR/SuperSQL/supersql.jar

#ライブラリの移動
mkdir -p $INSTALLDIR/SuperSQL/libs
cp -r $SCRIPT_DIR/lib/* $INSTALLDIR/SuperSQL/libs/

#ssql実行ファイルの移動
mkdir -p $HOME/bin
cat << EOS > ./ssql.sh
#!/bin/sh

#supersql.jarの位置指定
CLASSDIR=$INSTALLDIR/SuperSQL

EOS

cat << 'EOS' >> ./ssql.sh
#オプションのチェック
files=()
conf=""
options=()
VMs=()
FLAG_filespace=
FLAG_confspace=
for OPT in "$@"
do
	case "$OPT" in
		'-f' )
			FLAG_file=1
			FLAG_filespace=1
			FLAG_confspace=
			;;
		'-c' )
			FLAG_filespace=
			FLAG_confspace=1
			FLAG_conf=1
			;;
		'-v' | '--version' | '-version' )
			FLAG_filespace=
			FLAG_confspace=
			FLAG_version=1
			;;
		-D*)
			VMs+=($OPT)
			;;
		-* )
			FLAG_filespace=
			FLAG_confspace=
			options+=($OPT)
			;;
		* )
			if [ "$FLAG_filespace" ]; then
				files+=($OPT)
			elif [ "$FLAG_confspace" ]; then
				conf=$OPT
			else
				options+=($OPT)
			fi
			;;
	esac
	shift
done

if [ "$FLAG_version" ]; then
	echo "java -cp $CLASSDIR/libs/*:$CLASSDIR/supersql.jar supersql.FrontEnd -v"
	java -cp $CLASSDIR/libs/*:$CLASSDIR/supersql.jar supersql.FrontEnd -v
	exit 0
fi

files_full=()
#ssqlファイル指定
for file in ${files[@]}
do
	if [ ! -e $file ]; then
   		echo "Not found: $file"
   		exit 1
	fi
	cd `dirname $file`
	file_path=`pwd`/`basename $file`
	files_full+=($file_path)
done

#configファイル
if [ "$FLAG_conf" ]; then
	if [ ! -e $conf ]; then
   		echo "Not found: $conf"
   		exit 1
	fi
	cd `dirname $conf`
	conf_path=`pwd`/`basename $conf`
	options+=("-c" $conf_path)
fi

for o in ${options[@]}
do
	option_str+="${o} "
done


for file in ${files_full[@]}
do
	echo "java ${VMs} -cp $CLASSDIR/libs/*:$HOME/SuperSQL/supersql.jar supersql.FrontEnd -f ${file} ${option_str}"
	java $VMs -cp $CLASSDIR/libs/*:$CLASSDIR/supersql.jar supersql.FrontEnd -f $file $option_str
done
EOS
mv ssql.sh $HOME/bin/ssql
chmod 755 $HOME/bin/ssql

if [ `echo $PATH | grep "${HOME}/bin"` ]; then
	:
else
	export PATH=$PATH:$HOME/bin
fi
