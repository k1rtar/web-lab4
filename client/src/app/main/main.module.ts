import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainHitsComponent } from './main-hits/main-hits.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';

@NgModule({
  declarations: [
    MainHitsComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatTableModule
  ]
})
export class MainModule { }
