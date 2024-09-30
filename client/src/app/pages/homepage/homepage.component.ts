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
      alt: '',
      src: 'assets/wallpaper/d1.png',
    },
    {
      url: '/drop-y',
      alt: '',
      src: 'assets/wallpaper/d2.png',
    },
    {
      url: '/drop-z',
      alt: '',
      src: 'assets/wallpaper/d3.png',
    },
  ];
}
