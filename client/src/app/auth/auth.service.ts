import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable} from 'rxjs';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:29485';


  constructor(private http: HttpClient, private router: Router) {
    if (localStorage['id_token']) {
      router.navigate(['/main']);
    }
  }

  status(): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/auth/status`, {});
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/auth/login`, {username, password});
  }

  register(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/auth/registration`, {username, password});
  }

  logout(): Promise<void> {
    return new Promise((resolve) => {
      localStorage.removeItem('id_token');
      localStorage.removeItem('username');
      this.router.navigate(['/auth'])
      resolve();
    });
  }
}

