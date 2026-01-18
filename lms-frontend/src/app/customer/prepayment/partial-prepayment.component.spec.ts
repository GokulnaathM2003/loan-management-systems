import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartialPrepaymentComponent } from './partial-prepayment.component';

describe('PartialPrepaymentComponent', () => {
  let component: PartialPrepaymentComponent;
  let fixture: ComponentFixture<PartialPrepaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PartialPrepaymentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartialPrepaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
