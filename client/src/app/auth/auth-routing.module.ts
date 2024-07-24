import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AuthHelperComponent } from "./auth-helper/auth-helper.component";
import { AuthLogoutGuard } from "./authlogout.guard";
// @ts-ignore
import { EmptyComponent } from "../empty.component";

const routes: Routes = [
    { path: 'auth', component:  AuthHelperComponent},
    { path: 'auth/logout', canActivate: [AuthLogoutGuard], component: EmptyComponent},
  ];

  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
  })
  export class AuthRoutingModule {}
