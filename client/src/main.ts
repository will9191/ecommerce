import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

bootstrapApplication(AppComponent, appConfig).catch((err) =>
  console.error(err)
);

function setNavHeight() {
  const nav = document.querySelector('navbar') as HTMLElement;
  const root = document.querySelector(':root') as HTMLElement;
  root.style.setProperty('--navbar-height', `${nav?.clientHeight}`);
}

window.addEventListener('resize', setNavHeight);
window.addEventListener('DOMContentLoaded', setNavHeight);