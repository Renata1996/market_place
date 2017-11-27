var app = new Vue({
el: '#form',
data: {
messageLogin: '',
messagePassword: '',
login: '',
password: '',
},
methods: {
validateInput: function () {

    if(this.login==''){
        this.messageLogin='Login should not be empty!';
    } else if(this.login!=''){
        this.messageLogin='';
    }
},
validatePassword: function(){
      if(this.password==''){
           this.messagePassword='Password should not be empty!';
       }else if(this.password.length < 6 ){
           this.messagePassword='Password must be > 6';
        }else{
           this.messagePassword='';
        }
},
}
})