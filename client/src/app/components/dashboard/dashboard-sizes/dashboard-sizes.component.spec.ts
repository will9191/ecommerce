import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardSizesComponent } from './dashboard-sizes.component';

describe('DashboardSizesComponent', () => {
  let component: DashboardSizesComponent;
  let fixture: ComponentFixture<DashboardSizesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardSizesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DashboardSizesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
