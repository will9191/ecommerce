import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarComponent } from './navbar.component';

describe('NavbarComponent', () => {
  let component: NavbarComponent;
  let fixture: ComponentFixture<NavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavbarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

function setNavHeight() {
  const nav = document.querySelector('navbar') as HTMLElement;
  const root = document.querySelector(':root') as HTMLElement;
  root.style.setProperty('--navbar-height', `${nav?.clientHeight}`);
}

window.addEventListener('resize', setNavHeight);
window.addEventListener('DOMContentLoaded', setNavHeight);