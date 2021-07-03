/**
 * 给指定的字符串右边补齐给定长度的空字符
 */
export function fillCharsRight(str,len){
  var strTmp = "";
  if(str.length<len) {
    for (var i = 0; i < (len - str.length); i++) {
      // eslint-disable-next-line no-useless-escape
      strTmp += ' ';
    }
  }
  strTmp = str + strTmp
  return strTmp;
}

/**
 * 获取url参数
 */
export function getQueryVariable(variable) {
  var query = window.location.search.substring(1);
  var vars = query.split("&");
  for (var i=0;i<vars.length;i++) {
    var pair = vars[i].split("=");
    if(pair[0] == variable){return pair[1];}
  }
  return '';
}
