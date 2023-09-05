Vault set up in local windows
-------------------------------
Go to Hashicorp download Page and download Hashicorp Vault.

This is a zip file. Unzip it contains vault.exe. Create a file name vaultconfig.hcl for configuring vault on startup.


1
storage "file" {
2
    path= "./vault-data"
3
}listener "tcp" {
4
  address = "127.0.0.1:8200"
5
  tls_disable =1        
6
}disable_mlock=true




Open a command prompt and run the following vault commands-

vault server -config ./vaultconfig.hcl

Vault is now started. Open another command prompt and run the following commands-

set VAULT_ADDR=http://localhost:8200

vault operator init

set VAULT_TOKEN=s.wO85qvAKuzL4QQifLE9N5aiq

vault status

We can see here that the Vault is sealed. We need to unseal it.

Java
1
vault operator unseal Y2fJhNi5AXU3HdShQ4p0bZ/KfXVWU3ZYRwaecnlkEYYP
2
vault operator unseal XS/NJF3X/OcrVzoM9a5oSG2D7Mls2+WCE104RgCAJLrH
3
vault operator unseal W+W/R7Dwgaj+qwPsrKo3RBDxSzrfoW917AwgoAzvFcOT



Next enable a key value store with named secrets

vault secrets enable -path=secret/ kv

Store the MySql username and password in the Hashicorp Vault

vault kv put secret/javainuseapp dbusername=root

vault kv put secret/javainuseapp dbpassword=root



----------------------------------------------------------------



D:\project\software\vault_1.14.1>set VAULT_ADDR=http://localhost:8200

D:\project\software\vault_1.14.1>vault operator init
Unseal Key 1: u3ZI2yFPpPGCtGHXK96xAtUb5/iti0sDxwmR/gqBO34/
Unseal Key 2: ZpXURGev+mJ5TRKbZTkG7tLN3LjqkDAq+HDsoXTVmTJk
Unseal Key 3: ChB91Ra2wj8WVm9W+A/MZ3/ytDGrSyT7hr/rS6cVKsD5
Unseal Key 4: H0HCWXsNcfGjJgsfjBAYomoPJ23PogtCyiIkHmkkmjNl
Unseal Key 5: reh7xyS0N436C5zs9L5lyF6rgDXADEZ4pb5ifdpnrLRg

Initial Root Token: hvs.z9LMlTjLYzIYeFWUMOPD7ZHD

Vault initialized with 5 key shares and a key threshold of 3. Please securely
distribute the key shares printed above. When the Vault is re-sealed,
restarted, or stopped, you must supply at least 3 of these keys to unseal it
before it can start servicing requests.

Vault does not store the generated root key. Without at least 3 keys to
reconstruct the root key, Vault will remain permanently sealed!

It is possible to generate new unseal keys, provided you have a quorum of
existing unseal keys shares. See "vault operator rekey" for more information.

D:\project\software\vault_1.14.1>set VAULT_TOKEN=s.wO85qvAKuzL4QQifLE9N5aiq

D:\project\software\vault_1.14.1>
D:\project\software\vault_1.14.1>vault status
Key                Value
---                -----
Seal Type          shamir
Initialized        true
Sealed             true
Total Shares       5
Threshold          3
Unseal Progress    0/3
Unseal Nonce       n/a
Version            1.14.1
Build Date         2023-07-21T10:15:14Z
Storage Type       file
HA Enabled         false

Steps to Unseal Vault 
-------------------------------

vault operator unseal u3ZI2yFPpPGCtGHXK96xAtUb5/iti0sDxwmR/gqBO34/
vault operator unseal ZpXURGev+mJ5TRKbZTkG7tLN3LjqkDAq+HDsoXTVmTJk
vault operator unseal ChB91Ra2wj8WVm9W+A/MZ3/ytDGrSyT7hr/rS6cVKsD5

