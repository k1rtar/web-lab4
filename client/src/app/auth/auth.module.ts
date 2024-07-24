import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthHelperComponent } from './auth-helper/auth-helper.component';
import { AuthRoutingModule } from './auth-routing.module';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AuthHelperComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    ReactiveFormsModule,
  ]
})
export class AuthModule { }
