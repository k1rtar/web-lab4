import { Injectable } from '@angular/core';
import {
  HttpInterceptor, HttpErrorResponse, HttpRequest, HttpHandler, HttpEvent
} from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, of, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error) => {
        if (error instanceof HttpErrorResponse && error.status === 401) {
          this.router.navigate(['/auth']);
          return of();
        }
        return throwError(() => {
          return error;
        });
      })
    );
  }
}
