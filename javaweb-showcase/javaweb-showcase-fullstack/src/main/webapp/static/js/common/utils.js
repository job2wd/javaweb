/**
 * dependencies: jquery.js
 * System Tools
 */
var pojo = (function($) {
    return {
    /**
     * judge value is null
     */
        isNull: function(value) {
            if (typeof value == "undefined") {
                return true;
            }
            return value == null;
        },
        isNotNull: function(value) {
            return !this.isNull(value);
        },
    /**
     * judge value is String
     */
        isString: function(value) {
            if (this.isNull(value)) {
                return false;
            }
            return typeof value === "string";
        },
    /**
     * judge func is function
     */
        isFunction: function(func) {
            if (this.isNull(func)) {
                return false;
            }
            return typeof func === "function";
        },
    /**
     * judge value is Object
     */
        isObject: function(value) {
            if (this.isNull(value)) {
                return false;
            }
            return typeof value === "object";
        },
    /**
     * judge value is empty
     */
        isEmpty: function(value) {
            if (this.isNull(value)) {
                return true;
            }
            return !/^(.+)$/.test(value);
        },
        isNotEmpty: function(value) {
            return !this.isEmpty(value);
        },
    /**
     * judge value is int
     */
        isInteger: function(value) {
            if (this.isEmpty(value)) {
                return false;
            }
            return /^[-\+]?\d+$/.test(value);
        },
    /**
     * judge value is float
     */
        isFloat: function(value) {
            if (this.isEmpty(value)) {
                return false;
            }
            return /^[-\+]?\d+(\.\d+)?$/.test(value);
        },
    /**
     * judge value is double
     */
        isDouble: function(value) {
            return this.isFloat(value);
        },
    /**
     * judge value is number
     */
        isNumber: function(value) {
            return this.isDouble(value);
        },
    /**
     * judge value is email
     */
        isEmail: function(value) {
            return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
        },
    /**
     * judge value is chinese
     */
        onlyChinese: function(value) {
            if (this.isEmpty(value)) {
                return false;
            }
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
    /**
     * judge value has chinese character
     */
        haveChinese: function(value) {
            if (this.isEmpty(value)) {
                return false;
            }
            return /.*[\u0391-\uFFE5]+.*/.test(value);
        },
    /**
     * judge value is english character
     */
        onlyEnglish: function(value) {
            if (this.isEmpty(value)) {
                return false;
            }
            return /^[A-z]+$/.test(value);
        },
    /**
     * judge value is '', \t, \r, \n, \s
     */
        isWhitespace: function(value) {
            if (this.isNull(value)) {
                return false;
            }
            return /[\r\t\n\s]*/.test(value);
        },
    /**
     * safe turn value to int
     */
        parseInt: function(value) {
            if (this.isInteger(value)) {
                return parseInt(value);
            }
            return 0;
        },
    /**
     * safe turn value to float
     */
        parseFloat: function(value) {
            if (this.isFloat(value)) {
                return parseFloat(value);
            }
            return 0;
        },
    /**
     * safe turn value to boolean, default false
     */
        parseBoolean: function(value) {
            if (value == null) {
                return false;
            }
            if (typeof value == "boolean") {
                return value;
            }
            return /^[tT][rR][uU][eE]$/.test(value);
        },
    /**
     * safe turn value to double
     */
        parseDouble: function(value) {
            return this.parseFloat(value);
        },
    /**
     * safe turn value to number
     */
        parseNumber: function(value) {
            return this.parseDouble(value);
        },
    /**
     * remove left and right witespace
     */
        trim: function(value) {
            return this.ltrim(this.rtrim(value));
        },
    /**
     * remove left witespace
     */
        ltrim: function(value) {
            if (this.isNull(value)) {
                return value;
            }
            return value.replace(/^[\t\s]+/, "");
        },
    /**
     * remove right witespace
     */
        rtrim: function(value) {
            if (this.isNull(value)) {
                return value;
            }
            return value.replace(/[\t\s]+$/, "");
        },
    /**
     * set cookie
     */
        setCookie: function(name, value, options) {
            options = options || {};
            this.extend(options, {
                path: false,
                domain: false,
                days: 14,
                secure: false,
                document: document
            });
            var cookie = name + "=" + encodeURIComponent(value);
            if (options.domain) {
                cookie += '; domain=' + options.domain;
            }
            if (options.path) {
                cookie += '; path=' + options.path;
            }
            if (options.days) {
                var date = new Date();
                date.setTime(date.getTime() + options.days * 24 * 60 * 60 * 1000);
                cookie += '; expires=' + date.toGMTString();
            }
            if (options.secure) {
                cookie += '; secure';
            }
            document.cookie = cookie;
        },
    /**
     * get cookie value
     */
        getCookie: function(name) {
            if (pojo.isEmpty(name)) {
                return null;
            }
            var cookies = document.cookie.match('(?:^|;)\\s*' + this.escapeRegExp(name) + '=([^;]*)');
            for (var i = 0; pojo.isNotNull(cookies) && (i < cookies.length); ++i) {
                var cookieInfos = cookies[i].split(/=/);
                if (name == cookieInfos[0]) {
                    return decodeURIComponent(cookieInfos[1]);
                }
            }
            return null;
        },
    /**
     * delete cookie
     */
        deleteCookie: function(name) {
            this.setCookie(name, "", {days: -1});
        },
    /**
     * wrap regexp string
     */
        escapeRegExp: function(target) {
            if (this.isNull(target)) {
                return null;
            }
            return target.replace(/([-.*+?^${}()|[\]\/\\])/g, '\\$1');
        },
    /**
     * return the base path
     * example: http://localhost:9080/template
     */
        getBasePath: function() {
            var strFullPath = document.location.href;
            var strPath = document.location.pathname;
            var pos = strFullPath.indexOf(strPath);
            var prePath = strFullPath.substring(0, pos);
            var postPath = strPath.substring(0, strPath.substring(1).indexOf('/') + 1);
            return prePath + postPath;
        },
    /**
     * return the context path
     * example: /template
     */
        getContextPath: function() {
            var strPath = document.location.pathname;
            return strPath.substring(0, strPath.substring(1).indexOf('/') + 1);
        },
    /**
     * return relative path absout the pojo.utils.js
     * example: absoult:/template/js/ relative: js/
     */
        getVirtualPath: function() {
            if (this._virtualPath == null) {
                var jsArray = document.getElementsByTagName("script");
                for (var i = 0; i < jsArray.length; i++) {
                    var path = jsArray[i].src;
                    if (path != null && /.*pojo\.utils\.js.*/.test(path)) {
                        var isHttp = /^(http)s?:.*$/.test(path);
                        var replace = (isHttp ? this.getBasePath() : this.getContextPath()) + "/";
                        this._virtualPath = path.substring(0, path.lastIndexOf("pojo.utils.js")).replace(replace, "");
                        break;
                    }
                }
            }
            return this._virtualPath;
        },
    /**
     * return absoult path absout the pojo.utils.js
     * example: absoult:/template/js/
     */
        getRealPath: function() {
            return this.getContextPath() + "/" + this.getVirtualPath();
        },
    /**
     * ajax request
     * options:
     * url
     * username
     * password
     * type: POST GET PUT DELETE etc.
     * success: function(data, textStatus){this; // the options for this ajax request}
     * error: function(XMLHttpRequest, textStatus, errorThrown) {this; // the options for this ajax request}
     * complete: function (XMLHttpRequest, textStatus) {
     *              this; // the options for this ajax request
     *           }
     *
     * data: request data
     * contentType: default "application/x-www-form-urlencoded"
     * encoding: default: "UTF-8"
     * async: default true
     * timeout: number
     */
        request: function(options) {
            this.extend(options || {}, {
                url: "#",
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                encoding: "UTF-8",
                async: true
            });
            options["contentType"] += (";charset=" + options["encoding"]);
            $.ajax(options);
        },
    /**
     * implement extend
     * @param child this is sub object
     * @param parent this is father object
     */
        extend: function(child, parent) {
            if (this.isNull(child) || this.isNull(parent)) {
                return;
            }
            for (var prop in parent) {
                if (this.isNotNull(child[prop])) {
                    continue;
                }
                try {
                	child[prop] = parent[prop];
                } catch(e) {
                	alert(e.message);
                }
            }
        },
        Browser: {
            IE:     !!(window.attachEvent &&
                       navigator.userAgent.indexOf('Opera') === -1),
            Opera:  navigator.userAgent.indexOf('Opera') > -1,
            WebKit: navigator.userAgent.indexOf('AppleWebKit/') > -1,
            Gecko:  navigator.userAgent.indexOf('Gecko') > -1 &&
                    navigator.userAgent.indexOf('KHTML') === -1,
            MobileSafari: !!navigator.userAgent.match(/Apple.*Mobile.*Safari/)
        },
        /**
         * query element by elements's id or element
         * @param elem element's id or element
         * @return null or jQuery element
         */
        safeGet: function(elem) {
    		var proxy = pojo.isString(elem) ? $("#" + elem): $(elem);
    		if (proxy.size() != 1) {
    			return null;
    		}
    		return proxy;
    	}
    };
})(jQuery);
/**
 * operate form
 */
(function($) {
    pojo.form = {
    /**
     * validate form elements
     * this support int, float, double, require, Chinese, email, datetime, date, time
     * you can define your validator by registerHandler(type, handler)
     */
        validate: function() {
            var args = arguments;
            if (args.length == 0) {
                return false;
            }
            var form = pojo.safeGet(args[0]);
            if (form == null) {
                return false;
            }
            var result = true;
            var model = this._getDefaultModel();
            if (args.length >= 2) {
                model = this._getModel(args[1]);
            }
            var elems = $("#" + form.attr("id") + " input[name]").add("#" + form.attr("id") + " select[name]").filter(":enabled");
            for (var i = 0; i < elems.length; ++i) {
                var elem = $(elems[i]);
                var models = elem.attr("models");
                if (pojo.isNull(models)) {
                    models = new Array();
                } else {
                    models = models.split(/[\s\t,]+/);
                }
                models.push(this._getDefaultModel());
                if (!this._contains(models, model)) {
                    continue;
                }
                var etype = elem.attr("dataType");
                if (pojo.isEmpty(etype)) {
                    continue;
                }
                var _validator = null;
                switch (etype) {
                    case "int":
                        _validator = pojo.form.IntegerValidator;
                        break;
                    case "float":
                        _validator = pojo.form.FloatValidator;
                        break;
                    case "double":
                        _validator = pojo.form.DoubleValidator;
                        break;
                    case "require":
                        _validator = pojo.form.RequireValidator;
                        break;
                    case "Chinese":
                        _validator = pojo.form.ChineseValidator;
                        break;
                    case "email":
                        _validator = pojo.form.EmailValidator;
                        break;
                    case "date":
                        _validator = pojo.form.CalendarValidator;
                        _validator.setPattern("date");
                        break;
                    case "datetime":
                        _validator = pojo.form.CalendarValidator;
                        _validator.setPattern("datetime");
                        break;
                    case "time":
                        _validator = pojo.form.CalendarValidator;
                        _validator.setPattern("time");
                        break;
                    default:
                        _validator = this.findHander(etype);
                }
                if (_validator == null) {
                    continue;
                }
                result = _validator.validate(elem);
                if (!result) {
                    break;
                }
            }
            return result;
        },
    /**
     * registe validate handler
     * this handler must be the class,who implements validate(elem)
     * the method validate(elem) return true or false
     */
        registerHander: function(customType, handler) {
            if (customType == null || handler == null) {
                return false;
            }
            if (this.findHander(customType)) {
                return false;
            }
            this._validateHandlers[customType] = handler;
            return true;
        },
        findHander: function(customType) {
            return this._validateHandlers[customType];
        },
        alert: function(message) {
            window.alert(message);
        },
        _getModel: function(model) {
            return model;
        },
        _contains: function(arr, item) {
            for (var i = 0; i < arr.length; ++i) {
                var elem = arr[i];
                if (elem == item) {
                    return true;
                }
            }
            return false;
        },
        _getDefaultModel: function() {
            return "default";
        },
        _validateHandlers: {}
    };

    pojo.form.IntegerValidator = {
        validate: function(elem) {
            elem = pojo.safeGet(elem);
            if (pojo.isNull(elem)) {
                pojo.form.alert("Element is not exists!");
                return false;
            }
            if (pojo.isInteger(elem.val())) {
                return true;
            }
            var errorMessage = elem.attr("errorMessage");
            if (pojo.isEmpty(errorMessage)) {
                errorMessage = "[" + (elem.attr("name") || elem.attr("id")) + "] must be integer!";
            }
            elem.select();
            pojo.form.alert(errorMessage);
            return false;
        }
    };

    pojo.form.FloatValidator = {
        validate: function(elem) {
            elem = pojo.safeGet(elem);
            if (pojo.isNull(elem)) {
                pojo.form.alert("Element is not exists!");
                return false;
            }
            if (pojo.isFloat(elem.val())) {
                return true;
            }
            var errorMessage = elem.attr("errorMessage");
            if (pojo.isEmpty(errorMessage)) {
                errorMessage = "[" + (elem.attr("name") || elem.attr("id")) + "] must be float!";
            }
            elem.select();
            pojo.form.alert(errorMessage);
            return false;
        }
    };

    pojo.form.DoubleValidator = {
        validate: function(elem) {
            elem = pojo.safeGet(elem);
            if (pojo.isNull(elem)) {
                pojo.form.alert("Element is not exists!");
                return false;
            }
            if (pojo.isDouble(elem.val())) {
                return true;
            }
            var errorMessage = elem.attr("errorMessage");
            if (pojo.isEmpty(errorMessage)) {
                errorMessage = "[" + (elem.attr("name") || elem.attr("id")) + "] must be double!";
            }
            elem.select();
            pojo.form.alert(errorMessage);
            return false;
        }
    };

    pojo.form.RequireValidator = {
        validate: function(elem) {
            elem = pojo.safeGet(elem);
            if (pojo.isNull(elem)) {
                pojo.form.alert("Element is not exists!");
                return false;
            }
            if (!pojo.isEmpty(elem.val())) {
                return true;
            }
            var errorMessage = elem.attr("errorMessage");
            if (pojo.isEmpty(errorMessage)) {
                errorMessage = "[" + (elem.attr("name") || elem.attr("id")) + "] Required!";
            }
            elem.select();
            pojo.form.alert(errorMessage);
            return false;
        }
    };

    pojo.form.ChineseValidator = {
        validate: function(elem) {
            elem = pojo.safeGet(elem);
            if (pojo.isNull(elem)) {
                pojo.form.alert("Element is not exists!");
                return false;
            }
            if (pojo.onlyChinese(elem.val())) {
                return true;
            }
            var errorMessage = elem.attr("errorMessage");
            if (pojo.isEmpty(errorMessage)) {
                errorMessage = "[" + (elem.attr("name") || elem.attr("id")) + "] must be Chinese!";
            }
            elem.select();
            pojo.form.alert(errorMessage);
            return false;
        }
    };

    pojo.form.EmailValidator = {
        validate: function(elem) {
            elem = pojo.safeGet(elem);
            if (pojo.isNull(elem)) {
                pojo.form.alert("Element is not exists!");
                return false;
            }
            if (pojo.isEmail(elem.val())) {
                return true;
            }
            var errorMessage = elem.attr("errorMessage");
            if (pojo.isEmpty(errorMessage)) {
                errorMessage = "[" + (elem.attr("name") || elem.attr("id")) + "] must be email!";
            }
            elem.select();
            pojo.form.alert(errorMessage);
            return false;
        }
    };

    pojo.form.CalendarValidator = {
        _datetime: /^\d{4}-\d{2}-\d{2}[\s\t]+\d{2}:\d{2}:\d{2}$/,
        _date: /^\d{4}-\d{2}-\d{2}$/,
        _time: /^\d{2}:\d{2}:\d{2}$/,
        _pattern: "datetime",
        validate: function(elem) {
            elem = pojo.safeGet(elem);
            if (pojo.isNull(elem)) {
                pojo.form.alert("Element is not exists!");
                return false;
            }
            if (this._test(elem.val())) {
                return true;
            }
            var errorMessage = elem.attr("errorMessage");
            if (pojo.isEmpty(errorMessage)) {
                errorMessage = "[" + (elem.attr("name") || elem.attr("id")) + "] must be " + this._pattern;
            }
            elem.select();
            pojo.form.alert(errorMessage);
            return false;
        },
        setPattern: function(pattern) {
            this._pattern = pattern;
        },
        _test: function(value) {
            switch (this._pattern) {
                case "date":
                    return this._date.test(value);
                case "datetime":
                    return this._datetime.test(value);
                case "time":
                    return this._time.test(value);
                default:
                    return false;
            }
        }
    };
})(jQuery);

