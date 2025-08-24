# react-native-nitro-system-navigation-bar

A high-performance React Native module built with Nitro Modules for controlling Android's System Navigation Bar. This library provides native-level performance for navigation bar manipulation and system UI control.

[![Version](https://img.shields.io/npm/v/react-native-nitro-system-navigation-bar.svg)](https://www.npmjs.com/package/react-native-nitro-system-navigation-bar)
[![Downloads](https://img.shields.io/npm/dm/react-native-nitro-system-navigation-bar.svg)](https://www.npmjs.com/package/react-native-nitro-system-navigation-bar)
[![License](https://img.shields.io/npm/l/react-native-nitro-system-navigation-bar.svg)](https://github.com/tconns/react-native-nitro-system-navigation-bar/LICENSE)

## Features

- **High Performance**: Built with Nitro Modules for native-level performance
- **Navigation Bar Control**: Hide/show system navigation bar
- **Immersive Modes**: Full control over immersive sticky modes
- **Color Customization**: Change navigation bar color with translucent support
- **Appearance Control**: Set light/dark navigation bar icons and text
- **Height Detection**: Get accurate navigation bar height
- **Modern Android Support**: Uses latest Android APIs (WindowInsets Controller for Android 11+)

## Requirements

- React Native v0.76.0 or higher
- Node 18.0.0 or higher
- Android API level 23 or higher

> [!IMPORTANT]  
> This library uses Nitro Modules. Make sure to install `react-native-nitro-modules` as a dependency.

## Installation

```sh
npm install react-native-nitro-system-navigation-bar react-native-nitro-modules
# or
yarn add react-native-nitro-system-navigation-bar react-native-nitro-modules
```

## Usage

```ts
import {
  getNavigationBarHeight,
  hideNavigationBar,
  showNavigationBar,
  enableImmersiveSticky,
  exitImmersiveMode,
  setNavigationBarColor,
  setLightNavigationBar
} from 'react-native-nitro-system-navigation-bar';

// Get navigation bar height
const height = getNavigationBarHeight();
console.log('Navigation bar height:', height);

// Hide navigation bar
hideNavigationBar();

// Show navigation bar
showNavigationBar();

// Enable immersive sticky mode (hide all system bars)
enableImmersiveSticky();

// Exit immersive mode (show all system bars)
exitImmersiveMode();

// Set navigation bar color (red with transparency)
setNavigationBarColor(0xff0000ff, true);

// Set navigation bar to use light icons/text
setLightNavigationBar(true);

// Set navigation bar to use dark icons/text
setLightNavigationBar(false);
```

## API Reference

### `getNavigationBarHeight(): number`

Returns the height of the system navigation bar in pixels.

**Returns:** `number` - The height in pixels

### `hideNavigationBar(): void`

Hides the system navigation bar using immersive sticky mode.

### `showNavigationBar(): void`

Shows the system navigation bar and exits immersive mode.

### `enableImmersiveSticky(): void`

Enables immersive sticky mode, hiding all system bars (status bar and navigation bar). The bars will reappear temporarily when the user swipes from the edges and hide again automatically.

### `exitImmersiveMode(): void`

Exits immersive mode and shows all system bars (status bar and navigation bar).

### `setNavigationBarColor(color: number, isTranslucent?: boolean): void`

Sets the navigation bar color.

**Parameters:**
- `color: number` - Integer color value (e.g., `0xff0000ff` for blue)
- `isTranslucent?: boolean` - Optional flag to enable translucent navigation bar

**Example:**
```ts
// Solid red navigation bar
setNavigationBarColor(0xffff0000);

// Translucent blue navigation bar
setNavigationBarColor(0xff0000ff, true);

// Transparent navigation bar
setNavigationBarColor(0x00000000, true);
```

### `setLightNavigationBar(isLight: boolean): void`

Sets the navigation bar text/icon appearance.

**Parameters:**
- `isLight: boolean` - `true` for light icons/text (use with dark backgrounds), `false` for dark icons/text (use with light backgrounds)

## Color Format

Colors should be provided as 32-bit integers in ARGB format:
- `0xAARRGGBB` where:
  - `AA` = Alpha (transparency)
  - `RR` = Red
  - `GG` = Green  
  - `BB` = Blue

**Examples:**
```ts
// Solid colors
const red = 0xffff0000;
const green = 0xff00ff00;
const blue = 0xff0000ff;
const white = 0xffffffff;
const black = 0xff000000;

// Semi-transparent colors
const semiTransparentRed = 0x80ff0000;
const transparentBlue = 0x400000ff;
```

## Android Version Support

This library automatically adapts to different Android versions:

- **Android 11+ (API 30+)**: Uses modern WindowInsets Controller
- **Android 8.0+ (API 26+)**: Uses system UI visibility flags
- **Android 6.0+ (API 23+)**: Basic navigation bar control

## Contributing

When changing spec files (`*.nitro.ts`), please re-run nitrogen:

```sh
npx nitro-codegen
```

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## Project Structure

- [`android/`](android/): Android native implementation (Kotlin, C++)
- [`ios/`](ios/): iOS native implementation (Swift, C++) 
- [`nitrogen/`](nitrogen/): Auto-generated files by Nitro Modules
- [`src/`](src/): TypeScript source code and API definitions
- [`nitro.json`](nitro.json): Nitro Module configuration
- [`package.json`](package.json): Package metadata and dependencies

## License

MIT © [Thành Công](https://github.com/tconns)