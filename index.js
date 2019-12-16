'use strict'
const JavaScriptObfuscator = require('javascript-obfuscator')
const config = hexo.config

function obfuscateJavaScript (str, high) {
    let debugProtection = high && !hexo.env.debug && config.obfuscator.debugProtection
    let domainLock = high && !hexo.env.debug ? config.obfuscator.domainLock : []
    let deadCodeInjectionThreshold = high ? 0.7 : 0.4
    let stringArrayThreshold = high ? 1 : 0.4

    let obfuscationResult = JavaScriptObfuscator.obfuscate(str,
        {
            // 输出一行
            compact: true,
            // 注入死代码 死代码系数 0.5或0.7或1
            deadCodeInjection: true,
            deadCodeInjectionThreshold: deadCodeInjectionThreshold,
            // debug保护
            debugProtection: debugProtection,
            debugProtectionInterval: debugProtection,
            // 禁止console
            disableConsoleOutput: debugProtection,
            // 域名锁定
            domainLock: domainLock,
            // 不重命名全局
            renameGlobals: false,
            // 字符串数组 旋转字符串数组 加密规则rc4 系数0.7或1
            rotateStringArray: true,
            stringArray: true,
            stringArrayEncoding: 'rc4',
            stringArrayThreshold: stringArrayThreshold,
            // unicode编码
            unicodeEscapeSequence: true
        }
    )
    return obfuscationResult.getObfuscatedCode()
}

if (config.obfuscator) {

    let highList = config.obfuscator.high
    let lowList = config.obfuscator.low
    if (highList || lowList) {
        hexo.extend.filter.register('after_render:js', function (str, data) {
            for (let i = 0; i < highList.length; i++) {
                if (data.path.endsWith(highList[i])) {
                    console.debug('高等级混淆', highList[i])
                    return obfuscateJavaScript(str, true)
                }
            }
            for (let i = 0; i < lowList.length; i++) {
                if (data.path.endsWith(lowList[i])) {
                    console.debug('低等级混淆', lowList[i])
                    return obfuscateJavaScript(str, false)
                }
            }
            return str
        })
    }

}
