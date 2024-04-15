import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ClientViewPageComponent} from './client-view-page.component';

describe('ClientViewPageComponent', () => {
    let component: ClientViewPageComponent;
    let fixture: ComponentFixture<ClientViewPageComponent>;

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [ClientViewPageComponent]
        });
        fixture = TestBed.createComponent(ClientViewPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
