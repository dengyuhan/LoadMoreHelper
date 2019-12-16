[javascript-obfuscator](https://github.com/javascript-obfuscator/javascript-obfuscator#javascript-obfuscator-options)的Hexo版本

### 在_config.yml加入配置
```
obfuscator:
  # debug保护
  debugProtection: true
  # 域名限制
  domainLock:
    - .xiandan.in
    - xiandan.in
  # 需要高等级混淆的js
  high:
    - /api.js
  # 需要低等级混淆的js 
  low:
    - /index.js
```
