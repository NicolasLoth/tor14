import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {Route, extract} from '@app/core';
import {MemberComponent} from './member.component';

import {AuthenticationGuard} from '../core/authentication/authentication.guard';

const routes: Routes = Route.withShell([
  {
    path: 'member',
    component: MemberComponent,
    canActivate: [AuthenticationGuard],
    data: {title: extract('member')}
  }
]);

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class MemberRoutingModule {
}
