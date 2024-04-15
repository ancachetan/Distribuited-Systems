import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientViewChartPageComponent } from './client-view-chart-page.component';

describe('ClientViewChartPageComponent', () => {
  let component: ClientViewChartPageComponent;
  let fixture: ComponentFixture<ClientViewChartPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientViewChartPageComponent]
    });
    fixture = TestBed.createComponent(ClientViewChartPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
