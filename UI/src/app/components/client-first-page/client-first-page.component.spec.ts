import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ClientFirstPageComponent} from './client-first-page.component';

describe('ClientFirstPageComponent', () => {
    let component: ClientFirstPageComponent;
    let fixture: ComponentFixture<ClientFirstPageComponent>;

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [ClientFirstPageComponent]
        });
        fixture = TestBed.createComponent(ClientFirstPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
