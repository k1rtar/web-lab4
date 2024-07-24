import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const idToken = localStorage.getItem('id_token');
    if (idToken && idToken !== undefined) {
      const cloned = request.clone({
        setHeaders: {
          Authorization: `Bearer ${idToken}`
        }
      });
      return next.handle(cloned);
    } else {
      return next.handle(request);
    }
  }
}
