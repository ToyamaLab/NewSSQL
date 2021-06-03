
/** フッタ(id="footer1")の処理 **/
//左フリック: [進む]、右フリック: [戻る]、タップ: [更新]
$(function() {
	//タップ時: [更新]
	$('#footer1').bind('tap',function(){
		location.reload();
	});

	//タッチイベントの取得
	$("#footer1").bind("touchstart touchmove touchend", touchHandler);
	function touchHandler(e) {  
		e.preventDefault();  
		var touch = e.originalEvent.touches[0];  

		if(e.type == "touchstart"){
			//タッチ開始時のX座標(startX)
			startX = touch.pageX; 
		}else if(e.type == "touchmove"){
			//移動距離(diffX) = スライド時のX座標 - 開始時のX座標
			diffX = touch.pageX - startX;
			if(( diffX > 0 ) || ( diffX < 0 )) {
				$('#footer1').css( "left", diffX );
			}
		}else if(e.type == "touchend"){
			if(diffX > 10) {		//右に10px以上移動: [戻る]
				history.go(-1);
			}else if(diffX < -10){	//左に10px以上移動: [進む]
				history.go(1);
			}else{
				$( '#footer1' ).animate({ left: 0 }, 200);
			} 
		}
	}
});


/** rel="external"と指定されていた場合は、別ウィンドウを開く **/
/**（W3C target="_blank" strict対策）**/
$(function(){
	$("a[rel='external']").click(function(){
		window.open($(this).attr("href"));
		return false;
	});
});


/** 画面サイズに応じて表示widthを変更 **/
/** 画面width > 閾値 のとき、widthを固定して表示をセンタリング **/
//PC: pc-width値に固定(指定なし(-1):350)
//モバイル: 縦=portrait-width値に固定(指定なし(-1):100%) ／ 縦=landscape-width値に固定(指定なし(-1):100%)
//初期load時
var windowWidthThreshold = 0;
$(document).ready(function(){
	$('[data-toggle="popover"]').popover();
	if(!bootstrap){
		windowWidthThreshold = getWindowWidthThreshold();
		if( $(window).width() > windowWidthThreshold ){
			//$("table").css("width","auto");
			$("#header1").css("width",windowWidthThreshold).css("margin","auto");
			$("#content1").css("width",windowWidthThreshold).css("margin","auto");
			$("#footer1").css("width",windowWidthThreshold).css("margin","auto");
			$("#LOGINpanel1").css("width",windowWidthThreshold).css("margin","auto");
			$("#LOGOUTpanel1").css("width",windowWidthThreshold).css("margin","auto");
		}
	}
});
//画面サイズが変更されたとき
window.onresize = function() {
	if(!bootstrap){
		windowWidthThreshold = getWindowWidthThreshold();
		if( $(window).width() > windowWidthThreshold ){
	    	//$("table").css("width","auto");
			$("#header1").css("width",windowWidthThreshold).css("margin","auto");
			$("#content1").css("width",windowWidthThreshold).css("margin","auto");
			$("#footer1").css("width",windowWidthThreshold).css("margin","auto");
			$("#LOGINpanel1").css("width",windowWidthThreshold).css("margin","auto");
			$("#LOGOUTpanel1").css("width",windowWidthThreshold).css("margin","auto");
		}else{
			//$("table").css("width","100%");
			$("#header1").css("width","100%");
			$("#content1").css("width","100%");
			$("#footer1").css("width","100%");
			$("#LOGINpanel1").css("width","97%");
			$("#LOGOUTpanel1").css("width","100%");
		}
	}
}
//画面width閾値を返す
function getWindowWidthThreshold(){
	if(!isSmartphone()){
		//PC
		if(pcWidth < 0)	return 350;
		else			return pcWidth;
	}else{
		//モバイル
		if(isPortrait()){
			//縦向き
			return portraitWidth;
		}else{
			//横向き
			return landscapeWidth;
		}
	}
}
//端末が縦向きかどうか
function isPortrait(){
	if($(window).width() < $(window).height())
		return true;
	return false;
}


$(document).on("pagebeforecreate",'[data-role=page]',function(e){
  $.ajaxSetup({cache : true});
  $.getScript('http://platform.twitter.com/widgets.js');
  $.ajaxSetup({cache : false});
});


$(document).on('pageshow', '[data-role=page]', function(e) {
  var src = '//www.facebook.com/plugins/like.php?href=';
  src += encodeURIComponent(location.href);
  src += '&send=false&layout=button_count&width=200&show_faces=true&action=like&colorscheme=light&height=21';
  $('.like-btn').attr('src', src);
});


function addBookmark(title,url) {
	//IE
	if(navigator.userAgent.indexOf("MSIE") > -1){
		window.external.AddFavorite(url, title);
	}
	//Firefox
	else if(navigator.userAgent.indexOf("Firefox") > -1){
		window.sidebar.addPanel(title, url, "");
	}
	//Opera
	else if(navigator.userAgent.indexOf("Opera") > -1){
		document.write('<div style="text-align:center"><a href="'+url+'" rel="sidebar" title="'+title+'">ブックマークに追加</a></div><br>');
	}
	//Netscape
	else if(navigator.userAgent.indexOf("Netscape") > -1){
		document.write('<div style="text-align:center"><input type="button" value="ブックマークに追加"');
		document.write(' onclick="window.sidebar.addPanel(\''+title+'\',\''+url+'\',\'\');"></div><br>');
	}
	else{
    	alert("このブラウザへのお気に入り追加ボタンは、Chrome/Safari等には対応しておりません。\nChrome/Safariの場合、CtrlキーとDキーを同時に押してください。\nその他の場合はご自身のブラウザからお気に入りへ追加下さい。");
  	}
}

$(document).on('pageshow', '#p-gallery', function(e){
	var currentPage = $(e.target);
	photoSwipeInstance = $("ul.gallery a", e.target).photoSwipe({},  currentPage.attr('id'));
}).on('pagehide', '#p-gallery', function(e){
	var currentPage = $(e.target),
	photoSwipeInstance = window.Code.PhotoSwipe.getInstance(currentPage.attr('id'));
	if (typeof photoSwipeInstance != "undefined" && photoSwipeInstance != null) {
		window.Code.PhotoSwipe.detatch(photoSwipeInstance);
	}
});

//for educ2018
// $(function(off_path, on_path){
// 	console.log("aaaaa "+off_path);
//      $('a img').hover(function(){
//         $(this).attr('src', on_path.toString());
//           }, function(){
//              if (!$(this).hasClass('currentPage')) {
//              $(this).attr('src', off_path.toString());
//         }
//    });
// });

/*
$(document).ready(function() {
	$( "[id=tabs]" ).tabs();
});
*/

/** <form> validation **/
/*
$(document).ready(function () {
	jQuery.validator.addMethod(
	  "jqValidate_TelephoneNumber", function(value, element) {
	     return this.optional(element) || new RegExp("^[0-9\-]+$").test(value);
	   }, "Please enter a valid telephone number."
	);
	jQuery.validator.addMethod(
	  "jqValidate_Alphabet", function(value, element) {
	     return this.optional(element) || new RegExp("^[a-zA-Z]+$").test(value);
	   }, "You can enter only the alphabet."
	);
	jQuery.validator.addMethod(
	  "jqValidate_AlphabetNumber", function(value, element) {
	     return this.optional(element) || new RegExp("^[0-9a-zA-Z]+$").test(value);
	   }, "You can enter only the alphabet and a number."
	);
});
*/
