import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SaveDeviceDialogComponent} from './save-device-dialog.component';

describe('SaveDeviceDialogComponent', () => {
    let component: SaveDeviceDialogComponent;
    let fixture: ComponentFixture<SaveDeviceDialogComponent>;

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [SaveDeviceDialogComponent]
        });
        fixture = TestBed.createComponent(SaveDeviceDialogComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
