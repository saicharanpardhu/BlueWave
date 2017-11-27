 
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import { MatSnackBar } from '@angular/material';

@Injectable()
export class HttperrorService implements HttpInterceptor {

  constructor(private snackBar: MatSnackBar){}
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req).do(event => {}, err => {
            if (err instanceof HttpErrorResponse && err.status == 401) {
                // handle 401 errors
                this.snackBar.open("Not connected");
            }
        });
    }
}