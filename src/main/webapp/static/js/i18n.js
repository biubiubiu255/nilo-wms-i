/**
 * 获取浏览器语言类型
 * @return {string} 浏览器国家语言
 */
function getNavLanguage() {
    var type = navigator.appName;
    var lang;
    if (type == "Netscape") {
        lang = navigator.language;
    } else {
        lang = navigator.userLanguage;
    }
    lang = lang.substr(0, 2);//获取浏览器配置语言前两位
    if (lang != 'zh' && lang != 'en') {
        lang = 'en';
    }
    return lang;
}

function loadI18n(lang) {

        $.i18n.properties({
            name: 'i18n',
            path: '/static/i18n/',
            mode: 'map',
            language: lang,
            encoding: 'UTF-8',
            cache: true
        });

}

function refreshI18n(element) {
    //var ele = $("button,span,a,title,lable");
    var ele = isEmpty(element) ? $("option[i18n],span[i18n]") : element.find("option[i18n],span[i18n]");
    for (var i=0;i<ele.length;i++){
        // 根据i18n元素的 name 获取内容写入
        $(ele[i]).html($.i18n.prop($(ele[i]).attr('i18n')));
    }
    layui.form.render('select');
}

function getI18nAttr(value) {
    return $.i18n.prop(value);
}