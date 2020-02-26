class State {
    value : String;
    probability : number;

    constructor(value, probability) {
        this.value = value;
        this.probability = probability;
    }

    getValue() : String{
        return this.value;
    }

    getProbability() : number{
        return this.probability;
    }
}