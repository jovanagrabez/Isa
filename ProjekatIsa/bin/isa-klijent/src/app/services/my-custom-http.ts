import {ConnectionBackend, Headers, Http, RequestOptions, RequestOptionsArgs, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import { AuthServiceService } from './auth-service.service';
import { Router } from '@angular/router';
import { HttpErrorResponse} from '@angular/common/http';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/observable/empty';
import {Injectable} from '@angular/core';



@Injectable()
export class MyCustomHttp extends Http {

  public router2:any;                                                                                                       
  constructor(_backend: ConnectionBackend, _defaultOptions: RequestOptions, private authService: AuthServiceService, private router:Router) {
    super(_backend, _defaultOptions);
    this.router2=router;
  }
  request(url: string, options?: RequestOptionsArgs): Observable<Response> {
    // On every request, we will add the Authorization header
    console.log("called request inter");
    const enhacedOptions = this.setAuthorizationHeader(options);
    return super.request(url, enhacedOptions)
      .catch(this.catch401);
  }
  get(url: string, options?: RequestOptionsArgs): Observable<Response> {
    // On every request, we will add the Authorization header
    console.log("called get iinter ");
    const enhacedOptions = this.setAuthorizationHeader(options);
    return super.request(url, enhacedOptions)
      .catch((err: HttpErrorResponse) => {
        if (err.status === 401) {
        this.router.navigate(['/login']);
        }
        return Observable.throw(err);
        }
        );
  }
  
  post(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
    console.log("called put inter");
    const enhacedOptions = this.setAuthorizationHeader(options);
    return super.post(url,body, enhacedOptions)
    .catch((err: HttpErrorResponse) => {
      if (err.status === 401) {
      this.router.navigate(['/login']);
      }
      return Observable.throw(err);
      }
      );
  }

  put(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
    console.log("called put inter");
    const enhacedOptions = this.setAuthorizationHeader(options);
    return super.put(url,body, enhacedOptions)
    .catch((err: HttpErrorResponse) => {
      if (err.status === 401) {
      this.router.navigate(['/login']);
      }
      return Observable.throw(err);
      }
      );
    }

  delete(url: string, options?: RequestOptionsArgs): Observable<Response> {
    console.log("called put inter");
    const enhacedOptions = this.setAuthorizationHeader(options);
    return super.delete(url, enhacedOptions)
    .catch((err: HttpErrorResponse) => {
      if (err.status === 401) {
      this.router.navigate(['/login']);
      }
      return Observable.throw(err);
      }
      );
    }

  // Request Interceptor to append Authorization Header
  private setAuthorizationHeader(options?: RequestOptionsArgs): RequestOptionsArgs {
    if (!options) {
      options = new RequestOptions();
    }
    if (!options.headers) {
      options.headers = new Headers();
    }
    const token = `Basic ${this.authService.getToken()}`;
    // Make a copy of headers on the RequestOptions and append 'Authorization' token
    const headers = {
      ...options.headers.toJSON(),
      Authorization: token
    };
    options.headers = new Headers(headers);
    return options;
  }
  // Response Interceptor
  private catch401(error: Response): Observable<any> {
    // Check if we had 401 
    
    if (error.status === 401) {
      console.log('401');
      //this.router.navigate(['/login']);
      this.router2.navigateByUrl("/view-hotels"); 
      return Observable.empty();
    }
    return Observable.throw(error);
  }
}
