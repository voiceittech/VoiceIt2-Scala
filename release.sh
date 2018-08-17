#!/bin/bash
commit=$(git log -1 --pretty=%B | head -n 1)
if [[ $commit = *"Release"* ]];
then
  version=$(curl -s https://api.github.com/repos/voiceittech/voiceit2-scala/releases/latest | grep '"tag_name":' |sed -E 's/.*"([^"]+)".*/\1/' | awk -F. -v OFS=. 'NF==1{print ++$NF}; NF>1{if(length($NF+1)>length($NF))$(NF-1)++; $NF=sprintf("%0*d", length($NF), ($NF+1)%(10^length($NF))); print}')
  curl -u $GITHUBUSERNAME:$GITHUBPASSWORD -H "Content-Type: application/json" --request POST --data '{"tag_name": "'$version'", "target_commitish": "master", "name": "'$version'", "body": "", "draft": false, "prerelease": false}' https://api.github.com/repos/voiceittech/voiceit2-scala/releases
fi

