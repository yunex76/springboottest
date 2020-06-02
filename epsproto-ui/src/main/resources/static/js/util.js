// 바이트수 계산
var calByte = {
	getByteLength : function(s) { // 바이트수 구하기

		if (s == null || s.length == 0) {
			return 0;
		}
		var size = 0;

		for ( var i = 0; i < s.length; i++) {
			size += this.charByteSize(s.charAt(i));
		}

		return size;
	},
		
	cutByteLength : function(s, len) { // 바이트수 자르기

		if (s == null || s.length == 0) {
			return 0;
		}
		var size = 0;
		var rIndex = s.length;

		for ( var i = 0; i < s.length; i++) {
			size += this.charByteSize(s.charAt(i));
			if( size == len ) {
				rIndex = i + 1;
				break;
			} else if( size > len ) {
				rIndex = i;
				break;
			}
		}

		return s.substring(0, rIndex);
	},

	charByteSize : function(ch) { // 한글자 바이트수

		if (ch == null || ch.length == 0) {
			return 0;
		}

		var charCode = ch.charCodeAt(0);

		if (charCode <= 0x00007F) {
			return 1;
		} else if (charCode <= 0x0007FF) {
			return 2;
		} else if (charCode <= 0x00FFFF) { 
			return 2; // 모두 2바이트로 계산한다.
			//return 3;
		} else {
			return 4;
		}
	}
};


function fnAjax(url, param, success_callback, error_callback, type, async, contentType) {
	var rtndata = {};
	if (type == null) type = 'post';
	if (async == false) async = true;
	if (contentType == null) contentType = 'application/json';
	$.ajax({
	    url: url,
	    type: type,
	    async: async,
	    contentType: contentType,
	    data : JSON.stringify(param),
	    success: function(data){
	    	rtndata = data;
    		try { if(success_callback != null) success_callback(data); } catch (e){return;}
	    },
	    error: function (jqXHR, textStatus, errorThrown) {
	        alert("서버에 오류가 발생하였습니다. 잠시 후 다시 요청 부탁드립니다.\n오류가 계속되는 경우 관리자에게 문의 바랍니다.");
	        try { if(error_callback != null) error_callback(jqXHR, textStatus, errorThrown); } catch (e){return;}
	    }
	});

	return rtndata;
}

function getFormData(id){
	return $('#'+id).serializeArray().reduce(function(obj, item) {
	    obj[item.name] = item.value;
	    return obj;
	}, {});
}
function isNull(asValue){
    if (asValue == null || asValue == undefined || asValue.toString().replace(/ /g,"") == ""){
        return true;
    }
    return false;
}
function isNum(asValue){
	if (isNull(asValue)) return false;
	for (var i = 0; i < asValue.length; i++) {
		if (asValue.charAt(i) < '0' || asValue.charAt(i) > '9'){
			return false;
		}
	}
	return true;
}
function isEmail(asValue) {
	var regExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return regExp.test(asValue); // 형식에 맞는 경우 true 리턴	
}
function isCelluar(asValue) {
	var regExp = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
	return regExp.test(asValue); // 형식에 맞는 경우 true 리턴
}
// 일반 전화번호 형식
function isPhone(asValue) {
	var regExp = /^(?:\d{2}|\d{3})-(?:\d{3}|\d{4})-\d{4}$/;
	return regExp.test(asValue); // 형식에 맞는 경우 true 리턴
}
function isJobPassword(asValue) {
	var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/; //  8 ~ 10자 영문, 숫자 조합
	return regExp.test(asValue); // 형식에 맞는 경우 true 리턴
}
// 날짜형식 체크
function isDate(asValue) {
	var date = new Date(asValue);
	if(isNaN(date.getTime())){
		return false;
	} else {
		return true;
	}
}
// 달력셋팅함수 - jquery ui datepicker
var fnDatepicker = function(selector, dateformat){
	if (selector == null || selector == undefined) {
		selector = ".date";
	}
	if (dateformat == null || dateformat == undefined) {
		dateformat = "yy-mm-dd";
	}
	var options = {
		dateFormat: dateformat,
		showMonthAfterYear: true,
		changeYear: true,
		changeMonth: true,
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'], // 월의 한글 형식.
		dayNamesMin: ['일','월','화','수','목','금','토'],
	    showOtherMonths: true,
	    selectOtherMonths: true,
	    yearRange: '1900:2030'
	};
	$(selector).datepicker(options);
};

// 에러 메세지 표시
function showError(objId, msg){
		$("#"+objId+"_error").html(msg).parent().show();
}
// 스크립터로 파라미터 받기
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
/*
 * 파일확장자 체크
 * whiteExt ,로 확장자 연결
*/
function isWhiteExt(filename, whiteExt){
	var bool = false;
	if (whiteExt == '') {
		bool = true;
	} else {
		var ext = filename.substring(filename.lastIndexOf(".")+1, filename.length).toLowerCase();
		var whiteExtArr = whiteExt.toLowerCase().split(",");
		for(var i=0; i<whiteExtArr.length; i++) {
			if(ext == whiteExtArr[i]){
				bool = true;
			}
		}
	}
	return bool;
}
// 
function limitLengthCutText(obj, limit){
	 var contentLength = calByte.getByteLength($(obj).val());
	 if( contentLength > limit ){
	   $(obj).val(calByte.cutByteLength($(obj).val(), limit));
	 }
 }

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function dateString(str) {
	if ( str == null || str == undefined || str.length < 8 ) {
		return "";
	}
	return str.substr(0,4)+'-'+str.substr(4,2)+'-'+str.substr(6,2)
}

function stringDate(str) {

	str = str.replace(/-/g,"");
	return str;
}
