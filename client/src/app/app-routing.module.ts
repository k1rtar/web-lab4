import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainHitsComponent } from './main/main-hits/main-hits.component';

const routes: Routes = [
  { path: '', redirectTo: 'main', pathMatch: 'full' },
  { path: 'main', pathMatch: 'full', component: MainHitsComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
