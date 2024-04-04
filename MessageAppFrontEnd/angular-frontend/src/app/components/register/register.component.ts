import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  submitEmail: string = "";
  submitPassword: string = "";
  submitPasswordRetype: string = "";

  constructor(private login: LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  registerUser(){
    if(this.submitPassword === this.submitPasswordRetype){
      this.login.registerUser(this.submitEmail, this.submitPassword).subscribe((resp) => {
        console.log(resp);
        this.router.navigate(['login']);
      })
    }
  }

  backToLogin(){
    this.router.navigate(['login']);
  }

}
