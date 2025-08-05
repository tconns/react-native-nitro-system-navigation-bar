import { NitroModules } from 'react-native-nitro-modules';
import type { NitroSystemNavigationBar } from './NitroSystemNavigationBar.nitro';

const NitroSystemNavigationBarHybridObject =
  NitroModules.createHybridObject<NitroSystemNavigationBar>('NitroSystemNavigationBar');

export function getNavigationBarHeight(): number {
  return NitroSystemNavigationBarHybridObject.getNavigationBarHeight()
}

export function hideNavigationBar(): void {
  NitroSystemNavigationBarHybridObject.hideNavigationBar();
}

export function showNavigationBar(): void {
  NitroSystemNavigationBarHybridObject.showNavigationBar();
}

export function enableImmersiveSticky(): void {
  NitroSystemNavigationBarHybridObject.enableImmersiveSticky();
}

export function exitImmersiveMode(): void {
  NitroSystemNavigationBarHybridObject.exitImmersiveMode();
}

export function setNavigationBarColor(color: number, isTranslucent?: boolean): void {
  NitroSystemNavigationBarHybridObject.setNavigationBarColor(color, isTranslucent);
}

export function setLightNavigationBar(isLight: boolean): void {
  NitroSystemNavigationBarHybridObject.setLightNavigationBar(isLight);
}