import type { HybridObject } from 'react-native-nitro-modules';

export interface NitroSystemNavigationBar
  extends HybridObject<{ ios: 'swift'; android: 'kotlin' }> {
  // Get the height of the system navigation bar
  getNavigationBarHeight(): number;

  // Hide the system navigation bar (immersive sticky on Android)
  hideNavigationBar(): void;

  // Show the system navigation bar
  showNavigationBar(): void;

  // Enable immersive sticky mode (hide all system bars)
  enableImmersiveSticky(): void;

  // Exit immersive mode and show all system bars
  exitImmersiveMode(): void;

  /**
   * Set the navigation bar color.
   * @param color integer color value (e.g., 0xff0000ff for blue)
   * @param isTranslucent optional flag to enable translucent navigation
   */
  setNavigationBarColor(color: number, isTranslucent?: boolean): void;

  /**
   * Set the navigation bar text/icon appearance (light or dark).
   * @param isLight true for light icons/text, false for dark
   */
  setLightNavigationBar(isLight: boolean): void;
}
