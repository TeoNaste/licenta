
class User{
    private id : number;
    private username : String;
    private password : String;

    constructor(id,username, password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    getUsername(): String {
        return this.username
    }

    getPassword(): String{
        return this.password;
    }

    getId(): number{
        return this.id;
    }

}