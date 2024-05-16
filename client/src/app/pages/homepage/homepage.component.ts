import { Component, inject, NgZone } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarouselComponent } from '../../components/carousel/carousel.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [CommonModule, CarouselComponent, RouterModule],
  providers: [CarouselComponent],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss',
})
export class HomepageComponent {
  constructor() {
    inject(NgZone).runOutsideAngular(() => {
      setInterval(() => {}, 1000);
    });
  }
  images = [
    {
      url: '/drop-x',
      alt: 'black wallpaper',
      src: 'https://wallpapergod.com/images/hd/black-aesthetic-1920X1080-wallpaper-ch9tk3j5p772ls09.jpeg',
    },
    {
      url: '/drop-y',
      alt: 'white wallpaper',
      src: 'https://www.hdwallpapers.in/download/white_wallpaper_5_4k_hd_white-1920x1080.jpg',
    },
    {
      url: '/drop-z',
      alt: 'purple wallpaper',
      src: 'https://wallpaperswide.com/download/abstract_purple_earth-wallpaper-1920x1080.jpg',
    },
  ];
}
