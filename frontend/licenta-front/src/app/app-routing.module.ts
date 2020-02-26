import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MessageViewComponent } from './message-view/message-view.component';
import { TestingComponent } from './testing/testing.component';
import { PageNotfoundComponent } from './page-notfound/page-notfound.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { MessageAddComponent} from './message-add/message-add.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {
    path: 'welcome',
    component: WelcomeComponent
  },
  {
    path: 'add',
    component: MessageAddComponent
  },
  {
    path: 'messages/:received',
    component: HomeComponent,
    children: [
    {
      path: 'view/:id',
      component: MessageViewComponent
    }
]},
  { path: '', redirectTo: 'welcome', pathMatch: 'full'},
  { path: '**', component: PageNotfoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
