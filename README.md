# <img src="https://user-images.githubusercontent.com/39194432/90335713-da508f80-e011-11ea-9860-8632a28e956f.png">
<img src="https://img.shields.io/github/v/release/DayBreak365/NerfedShield?style=flat-square"></img>
<img src="https://img.shields.io/github/last-commit/DayBreak365/NerfedShield?style=flat-square"></img>
### 개발: [Daybreak](https://github.com/DayBreak365)
<br/>

**📋 License**

NerfedShield는 [MIT License](https://github.com/DayBreak365/NerfedShield/blob/master/LICENSE)에 따라 라이선스가 부여됩니다.

**📎 Contribute**

새로운 도전, 무한한 가능성. 추가 개발은 모든 개발자에게 허용됩니다. 자신만의 Fork를 만들고 자신만의 Build를 만드세요.
모두에게 알리고 싶은 참신한 코드를 작성했다면 Pull Request를 통해 플러그인에 기여해주세요.

**🔧 Build**
- **Maven**
    - NerfedShield 디렉토리에서 `mvn clean package` 명령을 실행하세요. 빌드된 플러그인 파일은 `target` 폴더에서 찾을 수 있습니다.
- **Craftbukkit Dependency**
    - NerfedShield 빌드시 다음 버전의 craftbukkit dependency가 필요합니다.
      - **1.9.4**, **1.10.2**, **1.11.2**, **1.12.2**, **1.13**, **1.13.2**, **1.14.4**, **1.15**, **1.16.1**, **1.16.2**
    - [최신 버전의 BuildTools](https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar)를 내려받은 후 `java -jar BuildTools.jar --rev <version>` 명령을 이용하여 각 버전의 craftbukkit을 빌드한 후 local maven repository에 install 해주세요.
