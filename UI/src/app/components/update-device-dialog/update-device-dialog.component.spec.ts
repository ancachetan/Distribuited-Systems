import {ComponentFixture, TestBed} from '@angular/core/testing';

import {UpdateDeviceDialogComponent} from './update-device-dialog.component';

describe('UpdateDeviceDialogComponent', () => {
    let component: UpdateDeviceDialogComponent;
    let fixture: ComponentFixture<UpdateDeviceDialogComponent>;

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [UpdateDeviceDialogComponent]
        });
        fixture = TestBed.createComponent(UpdateDeviceDialogComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
