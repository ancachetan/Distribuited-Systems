import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AdminFirstPageComponent} from './admin-first-page.component';

describe('AdminFirstPageComponent', () => {
    let component: AdminFirstPageComponent;
    let fixture: ComponentFixture<AdminFirstPageComponent>;

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [AdminFirstPageComponent]
        });
        fixture = TestBed.createComponent(AdminFirstPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
