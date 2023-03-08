workspace {

    model {
        cliente = person "Cliente"
        vendedor = person "Vendedor"
        escamboio = SoftwareSystem "escambo.io" {
            cliente -> this "Acessa através de um app"
            
            userService = container "User Service" {
                cliente -> this "Acessa via REST"
                vendedor -> this "Acessa via REST"
            }
            
            userDatabase = container "Users Database" {
                userService -> this "Lê e grava informações"
                tags database
            }
            
            
            
            productService = container "Products Service" {
                cliente -> this "Acessa via REST"
                vendedor -> this "Acessa via REST"
            }
            
            productDatabase = container "Products Database" {
                productService -> this "Lê e grava informações"
                tags database
            }
            
            orderService = container "Order Service" {
                cliente -> this "Acessa via REST"
                vendedor -> this "Acessa via REST"
            }
            
            OrderDatabase = container "Orders Database" {
                orderService -> this "Lê e grava informações"
                tags database
            }
            
            paymentService = container "Payment Service" {
                cliente -> this "Acessa via REST"
                vendedor -> this "Acessa via REST"
            }
            
            paymentDatabase = container "Payments Database" {
                PaymentService -> this "Lê e grava informações"
                tags database
            }
            
        }
        
        emailSystem = element "Sistema de envio de e-mails" "Processa os emails da aplicação." {
            escamboio -> this "Envia os e-mails através de uma fila"
        }
        paymentExternal = element "Sistema de Pagamentos" "Processa os pagamentos da aplicação." {
            escamboio -> this "Envia os e-mails através de uma fila"
        }
        pushNotificationService = element "Sistema de notificações" "Envia push notifications para os aplicativos." {
            escamboio -> this "Envia os e-mails através de uma fila"
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
        }
        

    }

}