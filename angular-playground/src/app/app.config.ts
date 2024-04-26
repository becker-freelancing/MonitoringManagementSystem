import { ApplicationConfig } from '@angular/core';
import {provideAnimations} from "@angular/platform-browser/animations";
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes), provideClientHydration(), provideAnimations()]
};
