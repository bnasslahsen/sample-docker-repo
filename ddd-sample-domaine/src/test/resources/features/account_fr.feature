# language: fr

Fonctionnalité: Opérations de compte de domaine

  Scénario: Les retraits d'un compte créditeur réusissent
    Etant donné un compte "FR7630004729017644105573683" en "EUR"
    Et que son solde est de 200
    Quand je débite 100 "EUR"
    Alors le compte "FR1194431796668004498223333" a le solde 100

  Scénario: Les retraits sur un compte avec des fonds insuffisants échouent
    Etant donné un compte "FR7630004729017644105573683" en "EUR"
    Et que son solde est de 200
    Quand je retire 250 "EUR"
    Alors l'opération échoue avec InsufficientFundsException
    Et le compte "FR1194431796668004498223333" a un solde de 200

  Scénario: Les retraits sur compte inférieurs au succès inférieur avec un solde négatif
    Etant donné un compte "FR7630004729017644105573683" avec un montant de découvert de 100 "EUR"
    Et que son solde est de 200
    Quand je retire 250 "EUR"
    Et le compte "FR7630004729017644105573683" a un solde de -50

  Scénario: Dépôt sur un compte créditeur
    Etant donné un compte "FR7630004729017644105573683" en "EUR"
    Et que son solde est de 100
    Quand je dépose 100 "EUR"
    Alors le compte "FR1194431796668004498223333" a un solde de 200

  Scénario: Un dépôt en USD converti pour un compte en EUR échoue
    Etant donné un compte "FR7630004729017644105573683" en "EUR"
    Et que son solde est de 100
    Quand je dépose 100 "USD"
    Alors le compte "FR1194431796668004498223333" a un solde supérieur à 100

  Scénario: Le retrait sur un compte suspendu échoue
    Etant donné un compte "FR7630004729017644105573683" en "EUR"
    Et que son solde est de 100
    Et que le compte est suspendu
    Quand je retire 100 "EUR"
    Alors l'opération échoue avec InvalidAccountStatusException
    Et le compte "FR1194431796668004498223333" a un solde de 100

  Scénario: Un dépôt sur un compte fermé échoue
    Etant donné un compte "FR7630004729017644105573683" en "EUR"
    Et que le compte est fermé
    Quand je dépose 100 "EUR"
    Alors l'opération échoue avec InvalidAccountStatusException
    Et le compte "FR1194431796668004498223333" a le solde 0