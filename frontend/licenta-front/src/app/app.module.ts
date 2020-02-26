import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TestingComponent } from './testing/testing.component';
import { HomeComponent } from './home/home.component';
import { MessageListComponent } from './message-list/message-list.component';
import { MessageViewComponent } from './message-view/message-view.component';
import { PageNotfoundComponent } from './page-notfound/page-notfound.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ApiService } from 'src/api/service/apiService';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule, MatCardModule, MatFormFieldModule, MatInputModule, MatAutocompleteModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppNavComponent } from './app-nav/app-nav.component';
import { MessageAddComponent } from './message-add/message-add.component';
import { MessageService } from 'src/api/service/messageService';
import { MessageCardComponent } from './message-list/message-card/message-card.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { PredictionService } from 'src/api/service/predictionService';

@NgModule({
  declarations: [
    AppComponent,
    TestingComponent,
    HomeComponent,
    MessageListComponent,
    MessageViewComponent,
    PageNotfoundComponent,
    WelcomeComponent,
    AppNavComponent,
    MessageAddComponent,
    MessageCardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    BrowserAnimationsModule,
    NgbModule,
    MatCardModule,
    ReactiveFormsModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule
  ],
  providers: [
    ApiService,
    MessageService,
    PredictionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
