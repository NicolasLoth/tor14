import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';
import { FlexLayoutModule } from '@angular/flex-layout';

import { MaterialModule } from '@app/material.module';
import { MemberRoutingModule } from './member-routing.module';
import { MemberComponent } from './member.component';

@NgModule({
  imports: [
    CommonModule,
    TranslateModule,
    FlexLayoutModule,
    MaterialModule,
    MemberRoutingModule
  ],
  declarations: [
    MemberComponent
  ]
})
export class MemberModule { }
