/** 
 * @fileoverview Common Javascript
 */
// defined global logger
var log = new Log(Log.ERROR, Log.alertLogger, false);

/**
 * JavaScript Common Util
 */
function CommonUtil () {
}
/**
 * the parameter is null or empty
 * @param {Object} v the value
 * @return {TypeName} return true if not null or empty(""), otherwise is false
 */
CommonUtil.isNullOrBlank = function (v) {
  if (v == null || v == "") {
    return true;
  }
  return false;
}
/**
 * the object is undefined
 * @param {Object} o the object
 * @return {TypeName} return true if the object is undefined, otherwise is false
 */
CommonUtil.isUndefined = function (o) {
  if (o == 'undefined' || typeof o == 'undefined') {
    return true;
  }
  return false;
}
/**
 * the object is null or undefined
 * @param {Object} o
 * @return {TypeName} 
 * @see see {@link CommonUtil#isNullOrBlank} and {@link CommonUtil#isUndefined}
 */
CommonUtil.isNullOrUndefined = function (o) {
	if (CommonUtil.isNullOrBlank(o) || CommonUtil.isUndefined(o)) {
    return true;
  }
  return false;
}
/**
 * the object is available
 * @param {Object} o
 * @return {TypeName} 
 */
CommonUtil.isAvailable = function (o) {
	if (o == null) {
		return false;
	}
  if (typeof (o) == "object") {
    if (o.object != null) {
      return true;
    }
  }
  return false;
}
/**
 * get element object by id
 * @param {Object} id
 * @return {TypeName} 
 */
CommonUtil.getElementObjectById = function (id) {
  if (CommonUtil.isNullOrBlank(id)) {
    log.debug("id is null or empty");
    return null;
  }
  return document.getElementById(id);
}
/**
 * get element value by element id
 * @param {Object} id the element id 
 * @return {TypeName} element value
 */
CommonUtil.getElementValueById = function (id) {
	var field = CommonUtil.getElementObjectById(id);
  if (CommonUtil.isNullOrUndefined(field)) {
    log.debug("not found element by id: " + id);
    return "";
  }
  return field.value;
}
/**
 * get element inner text by element id
 * @param {Object} id
 * @return {TypeName} 
 */
CommonUtil.getElementInnerTextById = function (id) {
  var field = CommonUtil.getElementObjectById(id);
  if (CommonUtil.isNullOrUndefined(field)) {
    log.debug("not found element by id: " + id);
    return "";
  }
  return field.innerText;
}
/**
 * get element value or inner text by element id
 * @param {Object} id
 * @return {TypeName} 
 */
CommonUtil.getElementValueOrInnerTextById = function (id) {
  var v = CommonUtil.getElementValueById(id);
  log.debug("element: " + id + ", value: " + v);
  if (CommonUtil.isNullOrUndefined(v)) {
    v = CommonUtil.getElementInnerTextById(id);
    log.debug("element: " + id + ", inner text: " + v);
  }
  return v;
}
/**
 * Close to browser window
 */
CommonUtil.closeWindow = function () {
  window.opener = null;
  window.open('', '_self');
  window.close();
}
/**
 * refresh to browser window
 */
CommonUtil.refreshWindow = function () {
  if (window.parent) {
      window.parent.location.reload();
  }
  window.location.reload();
}
/**
 * set the element does not show
 * @param {Object} id the element id
 */
CommonUtil.setDisplayNoneById = function (id) {
  var o = CommonUtil.getElementObjectById(id);
  if (CommonUtil.isNullOrUndefined(o)) {
    return;
  }
  o.style.display = "none";
  o.style.visibility = "hidden";
  //o.style.filter = "alpha(opacity=0)";
}
/**
 * include JavaScript file in JavaScript file
 * @param {Object} path
 */
CommonUtil.includeJSFile = function (path) {
  var sobj = document.createElement('script');
  sobj.type = "text/javascript";
  sobj.charset = "UTF-8";
  sobj.src = path;
  CommonUtil.appendElementInHead(sobj);
}
/**
 * include JavaScript file in JavaScript file if the specified parameter 'o' is not exist.
 * @param {Object} o
 * @param {Object} path
 */
CommonUtil.includeJSFileIfNotExist = function (o, path) {
	if (CommonUtil.isNullOrUndefined(o)) {
		CommonUtil.includeJSFile(path);
	}
}
/**
 * append an element to head tag in page
 * @param {Object} element
 */
CommonUtil.appendElementInHead = function (element) {
	document.getElementsByTagName('head')[0].appendChild(element);
}
/**
 * append an element to body tag in page
 * @param {Object} element
 */
CommonUtil.appendElementInBody = function (element) {
  document.getElementsByTagName('body')[0].appendChild(element);
}
/**
 * 创建空的二维数组
 * @param {Object} rows 要创建的二维数组行数
 * @return {TypeName} 
 */
CommonUtil.createTemp2DArray = function (rows) {
  var a = new Array();
  for ( var i = 0; i < rows; i++) {
    a[i] = new Array();
    a[i][0] = "";
    a[i][1] = "";
  }
  return a;
}
/**
 * 创建空的多维数组
 * @param {Object} rows 要创建的多维数组行数
 * @param {Object} columns 要创建的多维数组列数
 */
CommonUtil.createTempMultDArray = function (rows, columns) {
	var a = new Array();
	for ( var i = 0; i < rows; i++) {
    a[i] = new Array();
    for(var j = 0; j < columns; j++) {
    	a[i][j] = "";
    }
  }
}
/**
 * 对二维数组执行冒泡排序
 * @param {Object} darray 要排序的二维数组
 * @return {TypeName} 排序后的二维数组
 */
CommonUtil.bubbleSort4DArray = function (darray) {
  var t0;
  var t1;
  var exchange;
  var da = darray;
  for ( var i = 0; i < da.length; i++) {
    exchange = false;
    for ( var j = da.length - 2; j >= i; j--) {
      if ((da[j + 1][0]) < (da[j][0])) {
        t0 = da[j + 1][0];
        t1 = da[j + 1][1];
        da[j + 1][0] = da[j][0];
        da[j + 1][1] = da[j][1];
        da[j][0] = t0;
        da[j][1] = t1;
        exchange = true;
      }
    }
    if (!exchange)
      break;
  }
  return da;
}
/**
 * 转换10进制数为16进制数
 * @param {Object} number
 * @return {TypeName} 若传入参数不是数字，则返回空
 */
CommonUtil.toHex = function (number) {
	if (CommonUtil.isNullOrBlank(number) || typeof number != 'number') {
		return "";
	}
	return number.toString(16);
}

/**
 * 去除字符串两边空格
 * @memberOf {TypeName} 
 * @return {TypeName} 去除两边空格后的字符串
 */
String.prototype.trim = function() {
  return this.replace(/^\s+|\s+$/g,"");
}
/**
 * 去除字符串左边空格
 * @memberOf {TypeName} 
 * @return {TypeName} 去除左边空格后的字符串
 */
String.prototype.ltrim = function() {
  return this.replace(/^\s+/,"");
}
/**
 * 去除字符串右边空格
 * @memberOf {TypeName} 
 * @return {TypeName} 去除右边空格后的字符串
 */
String.prototype.rtrim = function() {
  return this.replace(/\s+$/,"");
}
/**
 * 字符串由 UTF-16 编码转换为 UTF-8 编码
 * @returns {String}
 */
String.prototype.UTF16To8 = function() {
	var str = this;
	var ret = "", i, len, charCode;
	len = str.length;
	for (i = 0; i < len; i++) {
		charCode = str.charCodeAt(i);
		if ((charCode >= 0x0001) && (charCode <= 0x007F)) {
			ret += str.charAt(i);
		} else if (charCode > 0x07FF) {
			ret += String.fromCharCode(0xE0 | ((charCode >> 12) & 0x0F));
			ret += String.fromCharCode(0x80 | ((charCode >> 6) & 0x3F));
			ret += String.fromCharCode(0x80 | ((charCode >> 0) & 0x3F));
		} else {
			ret += String.fromCharCode(0xC0 | ((charCode >> 6) & 0x1F));
			ret += String.fromCharCode(0x80 | ((charCode >> 0) & 0x3F));
		}
	}
	return ret;
}
/**
 * 字符串由 UTF-8 编码转换为 UTF-16 编码
 * @returns {String}
 */
String.prototype.UTF8To16 = function() {
	var str = this;
	var ret = "", i = 0, len, charCode;
	var charCode2, charCode3;
	len = str.length;
	while (i < len) {
		charCode = str.charCodeAt(i++);
		switch (charCode >> 4) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
			ret += str.charAt(i - 1);
			break;
		case 12:
		case 13:
			charCode2 = str.charCodeAt(i++);
			ret += String.fromCharCode(((charCode & 0x1F) << 6)
					| (charCode2 & 0x3F));
			break;
		case 14:
			charCode2 = str.charCodeAt(i++);
			charCode3 = str.charCodeAt(i++);
			ret += String.fromCharCode(((charCode & 0x0F) << 12)
					| ((charCode2 & 0x3F) << 6) | ((charCode3 & 0x3F) << 0));
			break;
		}
	}
	return ret;
}
/**
 * Base64编码
 * @returns {String}
 */
String.prototype.EncodeBase64 = function() {
	var str = this;
	str = str.UTF16To8();
	var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	var ret = "", i = 0, len;
	var charCode1, charCode2, charCode3;
	len = str.length;
	while (i < len) {
		charCode1 = str.charCodeAt(i++) & 0xff;
		if (i == len) {
			ret += base64EncodeChars.charAt(charCode1 >> 2);
			ret += base64EncodeChars.charAt((charCode1 & 0x3) << 4);
			ret += "==";
			break;
		}
		charCode2 = str.charCodeAt(i++);
		if (i == len) {
			ret += base64EncodeChars.charAt(charCode1 >> 2);
			ret += base64EncodeChars.charAt(((charCode1 & 0x3) << 4)
					| ((charCode2 & 0xF0) >> 4));
			ret += base64EncodeChars.charAt((charCode2 & 0xF) << 2);
			ret += "=";
			break;
		}
		charCode3 = str.charCodeAt(i++);
		ret += base64EncodeChars.charAt(charCode1 >> 2);
		ret += base64EncodeChars.charAt(((charCode1 & 0x3) << 4)
				| ((charCode2 & 0xF0) >> 4));
		ret += base64EncodeChars.charAt(((charCode2 & 0xF) << 2)
				| ((charCode3 & 0xC0) >> 6));
		ret += base64EncodeChars.charAt(charCode3 & 0x3F);
	}
	return ret;
}
/**
 * Base64解码
 * @returns {String}
 */
String.prototype.DecodeBase64 = function() {
	var str = this;
	str = str.UTF16To8();
	var base64DecodeChars = new Array(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62,
			-1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1,
			-1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
			15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1,
			26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42,
			43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);
	var charCode1, charCode2, charCode3, charCode4;
	var ret = "", i = 0, len;
	len = str.length;
	while (i < len) {
		do {
			charCode1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
		} while (i < len && charCode1 == -1);
		if (charCode1 == -1)
			break;
		do {
			charCode2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
		} while (i < len && charCode2 == -1);
		if (charCode2 == -1)
			break;
		ret += String
				.fromCharCode((charCode1 << 2) | ((charCode2 & 0x30) >> 4));
		do {
			charCode3 = str.charCodeAt(i++) & 0xff;
			if (charCode3 == 61)
				return ret;
			charCode3 = base64DecodeChars[charCode3];
		} while (i < len && charCode3 == -1);
		if (charCode3 == -1)
			break;
		ret += String.fromCharCode(((charCode2 & 0XF) << 4)
				| ((charCode3 & 0x3C) >> 2));
		do {
			charCode4 = str.charCodeAt(i++) & 0xff;
			if (charCode4 == 61)
				return ret;
			charCode4 = base64DecodeChars[charCode4];
		} while (i < len && charCode4 == -1);
		if (charCode4 == -1)
			break;
		ret += String.fromCharCode(((charCode3 & 0x03) << 6) | charCode4);
	}
	return ret;
}

/**
 * 自定义异常类
 * @param {Object} error_code
 * @param {Object} error_message
 * @param {Object} e
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function MyException (error_code, error_message, e) {
  var errorCode;
  var errorMessage = "Unknown Exception";
  var historyException = null;

  var expHelper = new ExceptionHelper(error_code, error_message, e);

  if (!CommonUtil.isNullOrBlank(error_code)) {
    errorCode = error_code;
  } else {
    errorCode = expHelper.getErrorCode();
  }
  if (!CommonUtil.isNullOrBlank(error_message)) {
    errorMessage = error_message;
  } else {
    errorMessage = expHelper.getErrorMessage();
  }
  if (!CommonUtil.isNullOrUndefined(e)) {
    historyException = e;
  }

  this.getErrorCode = function () {
    return errorCode;
  }
  this.getErrorMessage = function () {
    return errorMessage;
  }
  this.getHistoryException = function () {
    return historyException;
  }
}
/**
 * 异常辅助类
 * @param {Object} error_code
 * @param {Object} error_message
 * @param {Object} e
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function ExceptionHelper (error_code, error_message, e) {
  var errorCode;
  var errorMessage = "";

  if (!CommonUtil.isNullOrBlank(error_code) && !CommonUtil.isNullOrBlank(error_message)) {
    errorCode = error_code;
    errorMessage = error_message;
  } else {
    if (e instanceof MyException) {
      errorCode = e.getErrorCode();
      errorMessage = e.getErrorMessage();
    } else {
      var currentBrowser = new CurrentBrowser();
      if (CommonUtil.isNullOrBlank(error_code) && !CommonUtil.isNullOrUndefined(e)) {
        // 分别做不同的处理
        if (currentBrowser.isIE()) {
          errorCode = e.number;
        } else if (currentBrowser.isFirefox()) {

        } else if (currentBrowser.isChrome()) {

        } else if (currentBrowser.isOpera()) {

        } else if (currentBrowser.isSafari()) {

        } else {
          errorCode = 0;
        }
      }else if (!CommonUtil.isNullOrBlank(error_code)){
    	  errorCode = error_code;
      }
      if (CommonUtil.isNullOrBlank(error_message) && !CommonUtil.isNullOrUndefined(e)) {
        // 分别做不同的处理
        if (currentBrowser.isIE()) {
          errorMessage = e.message;
        } else if (currentBrowser.isFirefox()) {

        } else if (currentBrowser.isChrome()) {

        } else if (currentBrowser.isOpera()) {

        } else if (currentBrowser.isSafari()) {

        } else {
          errorMessage = "";
        }
      }else if (!CommonUtil.isNullOrBlank(error_message)){
    	  errorMessage = error_message;
      }
    }
  }
  this.getErrorCode = function () {
    return errorCode;
  }
  this.getErrorMessage = function () {
    return errorMessage;
  }
}
/**
 * 确定当前客户端操作系统（名称、版本等）<br>
 * 目前只支持以下操作系统的判断：<br>
 * Microsoft Windows, Apple MacOS, Linux, Unix
 * 
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function CurrentOS () {
  var OSName = "Unknown OS";
  var OSVersion;

  var isWindows = false;
  var isMacOS = false;
  var isLinux = false;
  var isUnix = false;

  var isWindows2000 = false;
  var isWindowsXP = false;
  var isWindows2003 = false;
  var isWindowsVista = false;
  var isWindowsNT = false;

  var ua = window.navigator.userAgent.toLowerCase();
  var uas = ua.split(";");

  var OSInfo = uas[2];
  OSVersion = OSInfo.substring(12, OSInfo.length);
  if (OSInfo.indexOf("windows") != -1) {
    OSName = "Windows";
    isWindows = true;
  } else if (OSInfo.indexOf("mac") != -1) {
    OSName = "MacOS";
    isMacOS = true;
  } else if (OSInfo.indexOf("x11") != -1) {
    OSName = "Unix";
    isUnix = true;
  } else if (OSInfo.indexOf("linux") != -1) {
    OSName = "Linux";
    isLinux = true;
  }
  if (isWindows) {
    if (OSVersion == "5.0") {
      isWindows2000 = true;
    } else if (OSVersion == "5.1") {
      isWindowsXP = true;
    } else if (OSVersion == "5.2") {
      isWindows2003 = true;
    } else if (OSVersion == "6.1") {
      isWindowsVista = true;
    } else {
      if (OSInfo.indexOf("nt") != -1) {
        isWindowsNT = true;
      }
    }
  }
  log.debug("current OS: " + OSName + ", version: " + OSVersion);
  this.getOSName = function () {
    return OSName
  }
  this.getOSVersion = function () {
    return OSVersion;
  }
  this.isWindows = function () {
    return isWindows;
  }
  this.isMacOS = function () {
    return isMacOS;
  }
  this.isLinux = function () {
    return isLinux;
  }
  this.isUnix = function () {
    return isUnix;
  }
  this.isWindows2000 = function () {
    return isWindows2000;
  }
  this.isWindowsXP = function () {
    return isWindowsXP;
  }
  this.isWindows2003 = function () {
    return isWindows2003;
  }
  this.isWindowsVista = function () {
    return isWindowsVista;
  }
  this.isWindowsNT = function () {
    return isWindowsNT;
  }
}
/**
 * 确定当前浏览器类型及版本号<br>
 * 目前只支持以下浏览器的判断：<br>
 * Microsoft IE, Mozillz Firefox, Google Chrome, Opera Opera, Apple Safari
 * 
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function CurrentBrowser () {
  var browserType;
  var browserVersion;
  var isIE = false;
  var isFirefox = false;
  var isChrome = false;
  var isOpera = false;
  var isSafari = false;

  var ua = navigator.userAgent.toLowerCase();
  var browserInfo;

  if (browserInfo = ua.match(/msie ([\d.]+)/)) {
    browserType = "IE";
    browserVersion = browserInfo[1];
    isIE = true;
  } else if (browserInfo = ua.match(/firefox\/([\d.]+)/)) {
    browserType = "Firefox";
    browserVersion = browserInfo[1];
    isFirefox = true;
  } else if (browserInfo = ua.match(/chrome\/([\d.]+)/)) {
    browserType = "Chrome";
    browserVersion = browserInfo[1];
    isChrome = true;
  } else if (browserInfo = ua.match(/opera.([\d.]+)/)) {
    browserType = "Opera";
    browserVersion = browserInfo[1];
    isOpera = true;
  } else if (browserInfo = ua.match(/version\/([\d.]+).*safari/)) {
    browserType = "Safari";
    browserVersion = browserInfo[1];
    isSafari = true;
  } else {
    browserType = "Unknown Browser";
    browserVersion = -1;
  }
  log.debug("current browser: " + browserType + ", version: " + browserVersion);  
  this.getBrowserType = function () {
    return browserType;
  }
  this.getBrowserVersion = function () {
    return browserVersion;
  }
  this.isIE = function () {
    return isIE;
  }
  this.isFirefox = function () {
    return isFirefox;
  }
  this.isChrome = function () {
    return isChrome;
  }
  this.isOpera = function () {
    return isOpera;
  }
  this.isSafari = function () {
    return isSafari;
  }
}

