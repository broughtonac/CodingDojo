class Deck {
    constructor() {
        this.suits = ["Hearts","Clubs","Diamonds","Spades"];
        this.values = ["Ace","2","3","4","5","6","7","8","9","Jack","Queen","King"];
        this.cards = [];
        for (let i = 0; i < this.suits.length; i++) {
            for (let j = 0; j < this.values.length; j++) {
                this.cards.push(new Card(this.suits[i], this.values[j]));
            }
        }
    }
    shuffle() {
        for (let i = this.cards.length - 1; i > 0; i--) {
            let j = Math.floor(Math.random() * (i + 1));
            [this.cards[i], this.cards[j]] = [this.cards[j], this.cards[i]];
        }
    }
    deal() {
        let i = Math.floor(Math.random() * this.cards.length);
        let dealing = this.cards.splice(i, 1);
        return dealing[0];
    }
    reset() {
        this.cards = [];
        for (let i = 0; i < this.suits.length; i++) {
            for (let j = 0; j < this.values.length; j++) {
                this.cards.push(new Card(this.suits[i], this.values[j]));
            }
        }
    }
}

class Card {
    constructor(suit, value) {
        this.suit = suit;
        this.value = value;
    }
}

class Player {
    constructor(name) {
        this.name = name;
        this.hand = [];
    }
    take(deck) {
        if (deck instanceof Deck) {
            this.hand.push(deck.deal());
        }
    }
    discard(suit, value) {
        let j = -1;
        for (let i = 0; i < this.hand.length; i++) {
            if (this.hand[i].suit == suit && this.hand[i].value == value) {
                j = i;
            }
        }
        if (j != -1) {
            this.hand.splice(j, 1);
        }
    }
}
