#!/bin/bash
binFolder=$(dirname "$0")

(${binFolder}/idam-create-caseworker.sh ccd-import ccd.docker.default@hmcts.net Pa55word11 Default CCD_Docker)

(${binFolder}/idam-create-caseworker.sh caseworker,caseworker-demo,caseworker-demo-solicitor solicitor@gmail.com)
(${binFolder}/idam-create-caseworker.sh caseworker,caseworker-demo,caseworker-demo-manager manager@gmail.com)
(${binFolder}/idam-create-caseworker.sh caseworker,caseworker-demo,caseworker-demo-manager test@gmail.com)

(${binFolder}/ccd-add-role.sh caseworker)
(${binFolder}/ccd-add-role.sh caseworker-demo)

(${binFolder}/ccd-add-role.sh caseworker-demo-solicitor)
(${binFolder}/ccd-add-role.sh caseworker-demo-manager)
