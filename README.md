<br/><br/>
[![PhonveVR](./.github/LogoPVR.png)](https://github.com/PhoneVR-Developers/PhoneVR/releases)<br/>
![LogoPVRv0 3](https://github.com/PhoneVR-Developers/PhoneVR/assets/4137788/b32a23f6-4264-45f9-99cc-bbdd593a8e94)

<br/>

<img src="./.github/rep1.png" width="50%"><img src="./.github/rep2.jpg" width="50%">
<sup>Pictures used for representational purposes</sup>

[![Build status](https://github.com/PhoneVR-Developers/PhoneVR/actions/workflows/build.yml/badge.svg)](https://github.com/PhoneVR-Developers/PhoneVR/actions)
![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/PhoneVR-Developers/PhoneVR?color=orange&include_prereleases)
[![Discord](https://img.shields.io/discord/745688786410930296?label=Discuss&logo=Discord&logoColor=white)](https://discord.gg/pNYtBNk)

Use Steam VR-enabled applications with your phone as VR HMD (*Head-mounted display*). The only Open-Source solution to similar commercial packages like VRidge, Riftcat, Trinus etc etc. *Common-network* can be any type of network between desktop and android app, even **USB Tethering** and **Mobile Hotspots** are supported.
<br/>

| Tabs | Links |
| --- | --- |
| Roadmap/Plan/Future Todo | [**Project Kanban**](https://github.com/orgs/PhoneVR-Developers/projects/1/views/1) | 
| Latest Release | [`Github Release`](https://github.com/PhoneVR-Developers/PhoneVR/releases/latest) |
| Previous Builds | [`Github Actions`](https://github.com/PhoneVR-Developers/PhoneVR/actions) |
| Issues | [`Github Issue Tracker`](https://github.com/PhoneVR-Developers/PhoneVR/issues) |
| Discuss/Chat/Help/Real-Time Updates/Any - Discord | [`Discord Server Invite Link`](https://discord.com/invite/pNYtBNk) |

## Contents
* [Requirements](#requirements)
* [Installation](#installation)
* [Advanced Configuration](#advanced-configuration)
* [Development](#development)
* [Troubleshooting](#troubleshooting)
* [Issue / Bug Reporting](#issue--bug-reporting)

## Requirements

A PC with *Windows 7 or above*, A smartphone with *Android 5.0 (Lollipop) or above* (_ALVR requires Android 8.0 or above_) with *OpenGL-ES 3.0 or above*, Steam and some SteamVR applications installed.

## Installation
PhoneVR(PVR) since version 2.1.0 PhoneVR will only work with ALVR.

| Differences                                                                    | ALVR Server                                  
| ------------------------------------------------------------------------------ | -------------------------------------------- 
| Server/Desktop Side                                                            | OpenVR API                                   
| Mobile Side                                                                    | Google Cardboard API                         
| GUI                                                                            | ✅                                           
| User friendly Installer                                                        | ✅                                           
| Serverside hardware(GPU) Encoding                                              | ✅                                           
| Serverside software Encoding                                                   | ✅                                           
| Automatic Firewall setting                                                     | ✅                                           
| Linux Support (\*Partial\*)                                                    | ✅                                           
| Developers                                                                     | [Alvr-org](https://github.com/alvr-org/ALVR) 
| Supports other HMDs ? Quest etc.                                               | ✅                                           
| Open-Source                                                                    | ✅                                           
| Network throttling                                                             | ✅                                           
| Average Latency                                                                | 150ms                                        
| VRApplication Graphics manipulation<br>(Brightness, resolution, contrast etc.) | ✅                                           
| Code backend                                                                   | Rust + C++                                   

### A. Installation using ALVR Server
* Currently only **[ALVR v20.8.0](https://github.com/alvr-org/ALVR/releases/tag/v20.8.0)** (latest stable) is supported by PhoneVR.
  * Older version
    - PhoneVR v1.4.0-beta -> ALVR v20.7.1
    - PhoneVR v1.3.0-beta -> ALVR v20.6.1
    - PhoneVR v1.2.0-beta -> ALVR v20.5.0
    - PhoneVR v1.1.0-beta -> ALVR v20.2.1
    - PhoneVR v1.0.0-beta -> ALVR v20.1.0
* Use the install guide of ALVR Server from [here](https://github.com/alvr-org/ALVR#install).

### Common Instructions for both Servers
* Install the Android Apk on your mobile from [`Releases`](https://github.com/PhoneVR-Developers/PhoneVR/releases/latest) page.
* Make sure that "Run in Background", "Auto Start"(Restart on Crash) permissions, if exists on your device(especially Xiaomi users), are given. Also make sure that any kind of 3rd party battery saver app dosen't kill PhoneVR when in background.
* After opening the installed app, Choose ALVR(recommended).

## Advanced Configuration 

### for ALVR Server
* This can be found [here](https://github.com/alvr-org/ALVR/wiki/Settings-guide) and [here](https://github.com/alvr-org/ALVR/wiki/Settings-tutorial).

## Development
This Project is presently under testing. But, pull requests are welcome. 

* Android App: App folder: `<root>/code/mobile/android/PhoneVR`
  * Compiled/Tested on `Android Studio 4.0.1`
  * For testing, this project has 2 Build Configs, Debug and Release. Debug has lots of debugging callouts to logcat from both JAVA and JNI.
  * There are also two build flavours: gvr and noGvr. The gvr flavour is a superset of the noGvr flavour that adds support for legacy PhoneVR server with Google VR SDK (deprecated).
    * The source code of the gvr flavour is in app/src/gvr/ and app/src/main/. The code of the noGvr flavour is in app/src/noGvr/ and app/src/main/. Respectively, any shared code in app/src/main/ has to be tested with both flavours.
    * The prepare-alvr-deps.sh script, which needs to be run before building supports the optional "nogvr" argument. If you run the script without arguments, you can build both flavours. If you run the script with the "nogvr" argument, you can only build the noGvr flavour.
  * PhoneVR is Android AVD compatible.
    * PhoneVR Legacy Server networking
      * PhoneVR with GVR APK when run on AVD automatically assigns 10.0.2.2 (default emu addr) to pcIP
      * After starting the AVD Emulator, need to run `adb forward tcp:33333 tcp:33333`, in terminal/cmd, this will enable PVRServer -> Android Emulator based PVRAndroidApp communication.
    * ALVR Server networking
      * Similar to ALVR-Over-USB (https://github.com/alvr-org/ALVR/wiki/ALVR-wired-setup-(ALVR-over-USB))
      * Once `ALVR Streamer (PC) Configuration` is done from the above link. Then you should, in terminal/cmd, execute
        * `adb forward tcp:9943 tcp:9943`
        * `adb forward tcp:9944 tcp:9944`
  
* External Vendor Libraries used (all Headers included in respective Projects):
  * Json v3.8.0 (https://github.com/nlohmann/json) (code\windows\libs\json)
  * Eigen v3.3.7 (https://gitlab.com/libeigen/eigen) (code\common\libs\eigen)
  * Asio v1.12.2 (https://sourceforge.net/projects/asio/files/asio/1.12.2%20%28Stable%29/) (code\common\libs\asio)
  * x264 0.161.r3015 MSVS15(2017) (https://github.com/ShiftMediaProject/x264)
  * GoogleVR 1.200 (https://github.com/googlevr/gvr-android-sdk) (code\mobile\android\libraries\jni)
  * ACRA v5.7 (https://github.com/ACRA/acra)
  * ALVR (https://github.com/alvr-org/ALVR) (code\mobile\android\PhoneVR\ALVR)

* Code styling
  * Repo enforces clang-format v16.0.0 (.clang-format) for C/C++ and spotless (.editorconfig, google-java-format, ktfmt) for Java/Kotlin files
  * To autofix clang-format'ing issues devs can use `make format`
  * To autofix spotless format issues devs can goto PhoneVR android project folder and use `./gradlew spotlessApply` or use [spotless-gradle](https://plugins.jetbrains.com/plugin/18321-spotless-gradle) plugin for AndroidStudio/IDEs

## Troubleshooting  
  
* Android App automatically comes back to "Discovery"(Home/AppStart) page after some VR Application usage
  - Check if `Android System Battery saver` or similar applications are killing the app when in background. Usually can be found is `Android Setting` -> `Application Manager` or `Application Settings` according to your Android device flavour/OEM.

* Lag while using VR App on mobile
  - Make sure you are not using debug variant of APK(unless you really want to).
  - All relevant component's FPS are displayed on Mobile device when `Settings -> Debug` is checked. You can find out what the bottleneck component in the whole chain from VRApplication on windows to GoogleVR SDK renderer on Android is and resolve it.
  
* Incase above mentioned things have not been helpful to you, then you might want to **Install Debug variant of APK** from `[PhoneVR.zip]/android/arm7/debug` which can help you/us to get more detailed logs.

## Issue / Bug Reporting

Prior to reporting your Issue/Bug, please check out the ongoing issues ([here](https://github.com/PhoneVR-Developers/PhoneVR/issues) and [here](https://github.com/alvr-org/ALVR/issues)), If you have the same issue, you can join and watch that discussion(s).

You can use the [`Github Issues`](https://github.com/PhoneVR-Developers/PhoneVR/issues) to submit PhoneVR related issues/bugs related to working of this Project or for any query.
For reporting ALVRServer related issues please use [`ALVR Github Issues`](https://github.com/alvr-org/ALVR/issues).

For quick resolution you may want to add the following data along with your issue/bug report,

<ins>For Installation-time or SteamVR-and-PhoneVR-linking issues,</ins>
* `steamvr.vrsettings` file in default location `C:\Program Files (x86)\Steam\config\steamvr.vrsettings`
* `vrserver.txt` file in default location `C:\Program Files (x86)\Steam\logs\vrserver.txt`
* Open a `cmd` in the follow default directory and copy paste output of the `vrcmd` command. `C:\Program Files (x86)\Steam\steamapps\common\SteamVR\bin\win64\vrcmd.exe`

<ins>For networking issues,</ins>

* Network Capture of Desktop `.pcap` file. How-to-get-it-> [Here](https://github.com/PhoneVR-Developers/PhoneVR/issues/26#issuecomment-683905486)
* Network Capture of Android/Mobile `.pcap` file. How-to-get-it-> [Here](https://github.com/PhoneVR-Developers/PhoneVR/issues/26#issuecomment-687640757)

<ins>If Unexpected android app crashes occur(unexpected means, you did **NOT** get</ins> [this](https://user-images.githubusercontent.com/4137788/102040730-9c168180-3df3-11eb-985a-5d8d6798ea5a.jpg) <ins>screen when PhoneVR crashed),</ins>

1. Open `PhoneVR` android app and let it crash.
2. Collect `bugreport-xxx.zip` from android device (How-to under *"Capture a bug report from a device"* paragraph [here](https://developer.android.com/studio/debug/bug-report#bugreportdevice))
3. Attach the `bugreport-xxx.zip` file in Discord or GitHub or mail to *"phonevr.crash@gmail.com"*

<ins>**Common files required for all kinds issues,**</ins>
- If using PhoneVR Legacy server
  * `pvrLog.txt` and/or `pvrDebugLog.txt` file(s) in default **windows** location `C:\Program Files (x86)\Steam\steamapps\common\SteamVR\drivers\PVRServer\logs`
  * `pvrLog.txt` and/or `pvrDebugLog.txt` file(s) in default **android** location `.../Android/data/virtualis.phonevr/files/PVR/`. *Optionally* you can also attach `Log` from your `Settings page` on the app.
- If using ALVR
  * ALVR logs from its server, are available in the GUI (you may which to enable Setting -> Logging -> [Log to disk](https://github.com/alvr-org/ALVR/wiki/Settings-guide#log-to-disk) if you want to keep saving the logs to file, even after alvr is closed 
* Screenshots / Videos are much more helpful for developers to understand the issues faster.
* And ofcourse, how to reproduce the issue :)
