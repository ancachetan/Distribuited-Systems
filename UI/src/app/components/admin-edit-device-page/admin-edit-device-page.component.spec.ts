import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AdminEditDevicePageComponent} from './admin-edit-device-page.component';

describe('AdminEditDevicePageComponent', () => {
    let component: AdminEditDevicePageComponent;
    let fixture: ComponentFixture<AdminEditDevicePageComponent>;

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [AdminEditDevicePageComponent]
        });
        fixture = TestBed.createComponent(AdminEditDevicePageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
