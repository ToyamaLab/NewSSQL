# SuperSQLとは

SuperSQLとは慶應義塾大学理工学部情報工学科の遠山研究室に於いて開発されているSQLの拡張言語です。データベースにあるデータを様々なメディアに任意の構造で出力することができます。詳しくは[ポータルサイト][1]をご覧ください。

# インストール手順(Docker)

## 必要なソフトウェア
- Docker
- Docker Compose

## 概要
この手順ではdocker-composeを用いてSSQL実行環境のあるコンテナとPostgreSQLコンテナを生成します。PostgreSQL等既にDB環境がある人はそちらを使用しても大丈夫です。その場合はSuperSQLディレクトリ内のDockerfileを実行してください。

## 手順
1. docker-compose.yml等のダウンロード

```
$ wget http://ssql.db.ics.keio.ac.jp/docker/ssql_docker.zip
$ unzip ssql_docker.zip
$ cd ssql_docker
```

2. docker-composeでコンテナの作成&実行

以下の`build`部分でyumでの必要なパッケージの取得やmavenのpackageが入ります。そのため時間が大体15~20分程度かかるのでお茶でも飲んで待っててください。docker hubに公開すればいいのですが取り急ぎということで…
```
$ docker-compose build
$ docker-compose up -d
```

3. SSQLのテスト実行

```
$ docker exec -it ssql bash
# vim ~/config.ssql
```

config.ssqlの内容はソースからビルドの初期設定の通りですがこの手順で進めた場合は以下のように記述すればサンプルデータベースに接続できます。
```
driver=postgresql
db=sample
host=db_pgsql
user=sample
outdir=/root/SuperSQL/ssql_result
port=5432
password=samplepwd
```
この状態で以下のようにコマンドを実行すればssql_resultに実行結果のhtmlが生成されます。
```
# ssql -f test.ssql
```
ssqlコマンドの引数についてはページ下部を参照してください。


## Coming soon
- SSQLコンテナにwebサーバ機能を同梱させてhttpアクセスで生成結果を確認する。
- SSQLコンテナのdocker hubでの公開。


# インストール手順(ソースからビルド)

## 必要なソフトウェア
- Java
- DBMS(PostgreSQL, MySQLなど)
- Maven

## 手順
1. GitHubからのクローン

    `$ git clone https://github.com/ToyamaLab/NewSSQL.git`

2. 初期設定

    ```
    $ cd NewSSQL
    $ ./configure
    ```
    これで初期設定が終わります。\
    `./configure`で行われるのは
    - $HOME直下にSuperSQLディレクトリが生成されます。その中にクエリを入れる`ssql_query`ディレクトリと出力結果を保存する`ssql_result`、ライブラリが入る`libs`が生成されます。`ssql_query`内にはテストクエリとして`test.ssql`が保存されます。また`mvn package`コマンドが実行され生成されたjarがSuperSQLディレクトリ直下に配置されます。
    - $HOME直下に`config.ssql`という設定ファイルが生成されます。設定内容は以下です。
        
        - diver: DBMSの指定。postgresql, mysql, sqliteなど 
        - db: データベース名。
        - host: データベースが動いているホスト。
        - user: データベースユーザ名。
        - outdir: 実行結果の保存場所。初期値は`$HOME/SuperSQL/ssql_result`
        - port: ポート。初期値は5432。
        - password: データベースのパスワード。

    - $HOME直下のbinディレクトリに`ssql`実行ファイルが配置されPATHを通します。

    これらの設定を$HOME以外にしてほしい場合は
    
    `$ ./configure.sh --installdir <dir_path>`
    
    で指定してください。(config.ssqlは$HOMEに、ssql実行ファイルは$HOME/binに配置されます)

    ここまで行なって
    
    `$ ssql -v`

    が実行できたら無事初期設定は終了しています。

3. テストクエリの実行
    
    以下$HOMEにインストールし同マシン内でPostgreSQLが動いているとします。適宜読み替えを行なってください
    ```
    $ createdb <db_name>
    $ cd NewSSQL/test_queries/config_file_test_DB
    $ psql -d <db_name> -f test.sql
    ```

    これでサンプルデータベスが作成されます。次に`config.ssql`を以下のように書き換えます。尚`<home_dir | install_dir>`に関してはパスを明示してください。
    ```
    driver=postgresql
    db=<db_name>
    host=localhost
    user=<user_name>
    outdir=<home_dir | install_dir>/SuperSQL/ssql_result
    port=5432
    ```
    ここまで終わったらSuperSQLフォルダに移動して実行をします。
    ```
    $ cd ~/SuperSQL/ssql_query
    $ ssql -f test.ssql
    ```
    このクエリはHTMLを生成するのでssql_resultフォルダにあるtest.htmlをブラウザで確認してください。ssqlコマンドのオプションは主なものが以下です。

    - -v, --version, -version: バージョン表示
    - -debug: デバッグコードの出力
    - -f: ファイル指定
    - -c: コンフィグファイルの指定(指定なしで$HOME/config.ssqlを参照します)



[1]:http://ssql.db.ics.keio.ac.jp/