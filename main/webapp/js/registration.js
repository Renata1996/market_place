var app = new Vue({
el: '#form',
data: {
messageLogin: '',
messagePassword: '',
messageReEnteredPassword: '',
messageAddress: '',
messageName: '',
error: '',
login: '',
password: '',
reEnteredPassword: '',
buillingAddress: '',
fullName: '',
},
methods: {
validateFields: function(){
     if(this.login == ''|| this.password == '' || this.reEnteredPassword == '' || this.buillingAddress == '' || this.fullName == ''){
                this.error = 'Field should not be empty!';
     }
},
validateLogin: function(){
    if(this.login == ''|| this.password == '' || this.reEnteredPassword == '' || this.buillingAddress == '' || this.fullName == ''){
            this.error = 'Field should not be empty!';
        } else{
            this.error = '';
        }
         if(this.login == this.fullName){
                this.messageLogin = 'Login and full name should be diffrent';
             }else{
                this.messageLogin = '';
             }
},
validatePassword: function(){
    if(this.password.length < 6 ){
        this.messagePassword = 'Password must be > 6';
     }else if(this.password != this.reEnteredPassword){
        this.messagePassword = 'Passwords must be equals';
     }else{
        this.messagePassword='';
     }
      if(this.login == ''|| this.password == '' || this.reEnteredPassword == '' || this.buillingAddress == '' || this.fullName == ''){
                     this.error = 'Field should not be empty!';
          }
          else
          {
          this.error="";}
},
validateFullName: function(){
 if(this.login == this.fullName){
        this.messageLogin = 'Login and full name should be diffrent';
     }else{
        this.messageLogin = '';
     }
     if(this.login == ''|| this.password == '' || this.reEnteredPassword == '' || this.buillingAddress == '' || this.fullName == ''){
                          this.error = 'Field should not be empty!';
               }
               else
               {
               this.error="";}
},
reset: function(){
    /*this.messageLogin= '';
    this.messagePassword= '';
    this.messageReEnteredPassword= '';
    this.messageAddress= '';
    this.messageName= '';
    this.error= '';*/
    this.login= '';
    this.password= '';
    this.reEnteredPassword= '';
    this.buillingAddress= '';
    this.fullName= '';
}
}
})