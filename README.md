

# react-native-nitro-system-navigation-bar

`react-native-nitro-system-navigation-bar` is a React Native module using Nitro Module to provide high-performance interaction with the Android SystemNavigationBar.

## Features

- Hide/show the navigation bar
- Switch between fullscreen modes: Lean Back, Immersive, Sticky Immersive
- Change navigation bar color
- Change navigation bar divider color
- Change Bar Mode (light/dark)
- Get current color of Navigation Bar/Status Bar
- Adjust layout with fitsSystemWindows
- Support for new Android APIs (Divider Color, Contrast Enforced)

## Installation

```sh
npm install react-native-nitro-system-navigation-bar
# or
yarn add react-native-nitro-system-navigation-bar
```

## Usage

```ts
import SystemNavigationBar from 'react-native-nitro-system-navigation-bar';

// Hide navigation bar
SystemNavigationBar.navigationHide();

// Show navigation bar
SystemNavigationBar.navigationShow();

// Switch to immersive fullscreen mode
SystemNavigationBar.immersive();

// Change navigation bar color
SystemNavigationBar.setNavigationColor('#FF0000', 'light');

// Get current bar colors
const barColors = await SystemNavigationBar.getBarColor('both');
console.log(barColors); // { status: '#757575', navigation: '#FF0000' }
```

## API

- `navigationHide()` – Hide navigation bar
- `navigationShow()` – Show navigation bar
- `leanBack()` – Lean Back mode
- `immersive()` – Immersive mode
- `stickyImmersive()` – Sticky Immersive mode
- `setBarMode(mode, bar)` – Change Bar Mode (light/dark, status/navigation/both)
- `fullScreen(enable)` – Enable/disable fullscreen
- `lowProfile()` – Low Profile mode
- `setNavigationColor(color, style, bar)` – Change navigation bar color
- `getBarColor(bar)` – Get current bar color
- `setFitsSystemWindows(enable)` – Adjust layout
- `setNavigationBarDividerColor(color)` – Change divider color
- `setNavigationBarContrastEnforced(enable)` – Enable/disable Contrast Enforced

## Contributing

When changing spec files (`*.nitro.ts`), please re-run nitrogen:

```sh
npx nitro-codegen
```

## Project Structure

- `android/`: Android source code (Kotlin, C++, build.gradle, CMakeLists.txt)
- `ios/`: iOS source code (Swift, C++)
- `nitrogen/`: Auto-generated files by nitrogen
- `src/`: TypeScript code, defines HybridObjects and API
- `nitro.json`: Nitro Module configuration
- `package.json`: npm package info

## License

MIT
