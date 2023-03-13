workspace {

    model {
        comprador = person "Comprador"
        vendedor = person "Vendedor"

        escamboio = SoftwareSystem "escambo.io" {
            description  "Aplicação desenvolvida através de uma arquitetura de microsserviços em que clientes e prestadores de serviços fechem negócio."
            comprador -> this "Visita escambo.io através de [HTTPS]"
            vendedor -> this "Acessa o aplicativo escambo.io para gestão de seus produtos e suas vendas."
      group Usuários {       
            userService = container "User Service" {
                description "Responsável por gerenciar os dados dos usuários."
                technology "Java 11 com Spring Boot"
            }
            
            userDatabase = container "Users Database" {
                userService -> this "Lê e grava informações"
                tags database
            }
      }
            
   group Produtos {          
            productService = container "Products Service" {
                description "Responsável por gerenciar os dados dos produtos."
                technology "Java 11 com Spring Boot"
            }
            
            productDatabase = container "Products Database" {
                productService -> this "Lê e grava informações" "SQL/Postgres"
                tags database
            }
   }



            group Pedidos {
                orderService = container "Order Service" {
                description "Responsável por gerenciar os dados de pedidos."
                technology "Java 11 com Spring Boot"

                this -> productService "Insere em tópicos de produto" "kafka" mensageria
                this -> userService "Insere em tópicos de usuário " "kafka" mensageria
            }
            
            OrderDatabase = container "Orders Database" {
                orderService -> this "Lê e grava informações" "NoSQL/MongoDB"
                tags database
            }
            }
            
 group Pagamentos {
            paymentService = container "Payment Service" {
                description "Responsável por gerenciar os dados de pagamentos."
                technology "Java 11 com Spring Boot"
                tags service

                this -> orderService "Insere em tópicos de Order" "kafka" mensageria
            }
            
            paymentDatabase = container "Payments Database" {
                
                PaymentService -> this "Lê e grava informações" "SQL/PostgreSQL"
                tags database
            }
                        
 }
            apiGateway = container "API Gateway" {

                description "API Gateway responsável por rotear as requisições feitas pelo frontend ou pelo app"
                tags apiGateway

                this -> userService "Faz chamadas síncronas" "JSON/HTTPS" sincrona
                this -> productService "Faz chamadas síncronas" "JSON/HTTPS" sincrona
                this -> paymentService "Faz chamadas síncronas" "JSON/HTTPS" sincrona
                this -> orderService "Faz chamadas síncronas" "REST/JSON/HTTPS" sincrona

                 technology "A Definir."
            }

            spa = container "SPA" {
                comprador -> this "REST/Json"
                vendedor -> this "REST/Json"
                this -> apiGateway "Faz chamadas síncronas" "REST/JSON" sincrona
                tags spa
                description "Single Page Application responsável por interair com os serviços através do API Gateway."
                technology "Angular 12"
            }

            app = container "App" {
                comprador -> this "Acessa o aplicativo container.io" sincrona
                this -> apiGateway "Faz chamadas síncronas" "REST/JSON" sincrona
                tags mobile

                description "Aplicativo responsável por interagir com os serviços através de um API Gateway."
                technology "Flutter"
            }

 
            
        }
        
        emailSystem = element "Sistema de envio de e-mails" "Processa os emails da aplicação." {
            escamboio -> this "Envia os e-mails através de uma fila"
            userService -> this "Envia e-mails ao usuário" "Mensageria" mensageria

        }
        paymentExternal = element "Sistema de Pagamentos" "Processa os pagamentos da aplicação." {
            escamboio -> this "Envia os e-mails através de uma fila"
            paymentService -> this "Recupera o status dos pagamentos" "REST/JSON"
        }
        pushNotificationService = element "Sistema de notificações" "Envia push notifications para os aplicativos." {
            escamboio -> this "Envia os e-mails através de uma fila"
            orderService -> this "Envia notificações ao aplicativo" "Mensageria" mensageria
            
        }
    }

    views {
        systemContext escamboio {
            include *
            autolayout lr
        }
        
        container escamboio {
            include *
            autolayout lr
            
        }

        theme default
        
        styles {
            element database {
                shape Cylinder
                background gray
            }
            element mobile {
                shape MobileDevicePortrait
                background gray
            }
            element spa {
                shape WebBrowser
                background gray
            }

            relationship mensageria {
                color #032c62
                colour #032c62
                dashed false
                thickness 2
            }

            element apiGateway {
                background #ed4b25
            }


        }
        

    }

}

