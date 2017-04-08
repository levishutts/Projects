import argparse

min_len = 6

def chk_pass(password):
    has_up = False
    has_low = False
    has_dig = False
    has_spec = False
    if len(password) < min_len:
        return False
    for ch in password:
        if ch.isalnum():
            if ch.isupper():
                has_up = True
            elif ch.islower():
                has_low = True
            else:
                has_dig = True
        else:
            has_spec = True
    return has_up and has_low and has_dig and has_spec

def main():
    parser = argparse.ArgumentParser(description="Check validity of password")
    parser.add_argument("Password", type=str, help="Proposed password (a string)")
    args = parser.parse_args()
    passwd = args.Password
    if chk_pass(passwd):
        print(passwd, "is a legal password")
    else:
        print(passwd, "is an illegal passwrod")

if __name__ == "__main__":
    main()
